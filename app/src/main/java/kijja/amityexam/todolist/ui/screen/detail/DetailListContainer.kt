package kijja.amityexam.todolist.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailListContainer(
    viewModel: DetailListViewModel = hiltViewModel(),
    userId: Int?,
) {
    // Collect the screen state from the view model
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    // Launch effect to trigger initial data loading when the screen is created
    LaunchedEffect(userId) {
        userId?.let {
            // Initialize data loading when userId is not null
            viewModel.initLoadData(userId)
        }
    }

    // Box layout to contain the DetailListScreenByState
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        // Display the DetailListScreenByState based on the current screen state
        DetailListScreenByState(
            viewState = screenState,
            onItemCheckedChange = viewModel::onCheckedItemChange,
        )
    }
}
