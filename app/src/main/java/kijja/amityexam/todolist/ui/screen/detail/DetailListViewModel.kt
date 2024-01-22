package kijja.amityexam.todolist.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kijja.amityexam.todolist.data.model.TodoListEntity
import kijja.amityexam.todolist.data.repository.TodoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailListViewModel
    @Inject
    constructor(private val todoListRepository: TodoListRepository) : ViewModel() {
        private val _screenState = MutableStateFlow<DetailListViewState>(DetailListViewState.Loading)
        val screenState = _screenState.asStateFlow()

        private var isInitData = false

        fun initLoadData(userId: Int) {
            if (isInitData) return
            isInitData = true

            loadData(userId)
        }

        private fun loadData(userId: Int) {
            viewModelScope.launch {
                todoListRepository.getTodoListByUserId(userId)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        _screenState.value = DetailListViewState.Error
                    }.collect { data ->
                        if (data.isEmpty()) {
                            _screenState.value = DetailListViewState.Error
                        } else {
                            _screenState.value = DetailListViewState.Success(data)
                        }
                    }
            }
        }

        fun onClickAddButton() {
        }

        fun onCheckedItemChange(item: TodoListEntity) {
        }
    }
