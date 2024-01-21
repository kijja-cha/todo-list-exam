package kijja.amityexam.todolist.ui.screen.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import kijja.amityexam.todolist.ui.graph.TodoListDestinations

@Composable
fun UserListContainer(
    viewModel: UserListViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    // Collect the screen state from the view model
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    // Launch effect to trigger initial data loading when the screen is created
    LaunchedEffect(Unit) {
        viewModel.initLoadData()
    }

    // Box layout to contain the UserListScreenByState
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Display the UserListScreenByState based on the current screen state
        UserListScreenByState(
            viewState = screenState,
            onClickUser = {
                // Navigate to the detail list screen when a user is clicked
                navHostController.navigate(TodoListDestinations.DETAIL_LIST.route)
            }
        )
    }
}

