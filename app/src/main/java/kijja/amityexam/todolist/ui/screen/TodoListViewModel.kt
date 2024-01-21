package kijja.amityexam.todolist.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kijja.amityexam.todolist.data.repository.TodoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel
    @Inject
    constructor(private val todoListRepository: TodoListRepository) : ViewModel() {
        private val _screenState = MutableStateFlow<TodoListState>(TodoListState.Loading)
        val screenState = _screenState.asStateFlow()

        private var isInitData = false

        fun initLoadData() {
            if (isInitData) return
            isInitData = true

            loadData()
        }

        fun loadData() {
            viewModelScope.launch {
                todoListRepository.getTodo()
                    .flowOn(Dispatchers.IO)
                    .catch {
                        _screenState.value = TodoListState.Error
                    }.collect { result ->
                        result.onSuccess { data ->
                            if (data.isEmpty()) {
                                _screenState.value = TodoListState.Error
                            } else {
                                _screenState.value = TodoListState.Success(data)
                            }
                        }.onFailure {
                            _screenState.value = TodoListState.Error
                        }
                    }
            }
        }
    }
