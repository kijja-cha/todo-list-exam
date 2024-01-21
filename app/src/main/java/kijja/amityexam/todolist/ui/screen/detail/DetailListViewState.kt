package kijja.amityexam.todolist.ui.screen

import kijja.amityexam.todolist.data.model.TodoListEntity

sealed class DetailListViewState {
    data object Loading : DetailListViewState()

    data object Error : DetailListViewState()

    data class Success(
        val data: List<TodoListEntity>? = null,
    ) : DetailListViewState()
}
