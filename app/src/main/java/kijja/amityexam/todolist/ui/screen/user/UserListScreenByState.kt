package kijja.amityexam.todolist.ui.screen.user

import androidx.compose.runtime.Composable
import kijja.amityexam.todolist.ui.component.ErrorView
import kijja.amityexam.todolist.ui.component.LoadingView

@Composable
fun UserListScreenByState(
    viewState: UserListViewState?,
    onClickUser: (String) -> Unit,
) {
    // Display different screens based on the current state
    when (viewState) {
        UserListViewState.Error -> {
            // Display an error screen
            ErrorView()
        }

        UserListViewState.Loading -> {
            // Display a loading screen
            LoadingView()
        }

        is UserListViewState.Success -> {
            // Display the success screen with the to-do list data
            UserListScreen(
                data = viewState.data,
                onClickUser = onClickUser,
            )
        }

        else -> {
            // Do nothing
        }
    }
}
