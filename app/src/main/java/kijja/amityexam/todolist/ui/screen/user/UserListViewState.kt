package kijja.amityexam.todolist.ui.screen.user

// Sealed class to represent different states of the user list view
sealed class UserListViewState {
    // Loading state, indicating that data is being loaded
    data object Loading : UserListViewState()

    // Error state, indicating that there was an issue loading the data
    data object Error : UserListViewState()

    // Success state, containing the loaded user list data
    data class Success(
        val data: List<Int>? = null,
    ) : UserListViewState()
}
