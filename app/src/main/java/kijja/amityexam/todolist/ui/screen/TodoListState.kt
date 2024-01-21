package kijja.amityexam.todolist.ui.screen

import kijja.amityexam.todolist.data.model.TodoListEntity

sealed class TodoListState {
    data object Loading : TodoListState()

    data object Error : TodoListState()

    data class Success(
        val data: List<TodoListEntity>? = null,
    ) : TodoListState()
}
