package kijja.amityexam.todolist.ui.screen.detail

import kijja.amityexam.todolist.data.model.TodoListEntity

// Sealed class to represent different states of the detail list view
sealed class DetailListViewState {
    // Loading state, indicating that data is being loaded
    data object Loading : DetailListViewState()

    // Error state, indicating that there was an issue loading the data
    data object Error : DetailListViewState()

    // Success state, containing the loaded to-do list data
    data class Success(
        val data: List<TodoListEntity>? = null,
    ) : DetailListViewState()
}
