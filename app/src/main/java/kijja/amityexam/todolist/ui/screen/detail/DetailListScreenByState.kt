package kijja.amityexam.todolist.ui.screen.detail

import androidx.compose.runtime.Composable
import kijja.amityexam.todolist.data.model.TodoListEntity
import kijja.amityexam.todolist.ui.component.ErrorView
import kijja.amityexam.todolist.ui.component.LoadingView

@Composable
fun DetailListScreenByState(
    viewState: DetailListViewState?,
    onItemCheckedChange: (TodoListEntity) -> Unit,
) {
    // Display different screens based on the current state
    when (viewState) {
        DetailListViewState.Error -> {
            // Display an error screen
            ErrorView()
        }

        DetailListViewState.Loading -> {
            // Display a loading screen
            LoadingView()
        }

        is DetailListViewState.Success -> {
            // Display the success screen with the to-do list data
            DetailListScreen(
                data = viewState.data,
                onItemCheckedChange = onItemCheckedChange,
            )
        }

        else -> {
            // Do nothing
        }
    }
}
