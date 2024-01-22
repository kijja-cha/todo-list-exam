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
        // MutableStateFlow to represent the screen state of the detail list
        private val _screenState = MutableStateFlow<DetailListViewState>(DetailListViewState.Loading)
        val screenState = _screenState.asStateFlow()

        // Flag to track whether the data has been initialized
        private var isInitData = false

        // Function to initialize data loading, ensuring it happens only once
        fun initLoadData(userId: Int) {
            if (isInitData) return
            isInitData = true

            // Trigger the data loading process
            loadData(userId)
        }

        // Function to load to-do list data asynchronously based on the user id
        private fun loadData(userId: Int) {
            viewModelScope.launch {
                // Execute the getTodoListByUserId function in the IO dispatcher
                todoListRepository.getTodoListByUserId(userId)
                    .flowOn(Dispatchers.IO)
                    .catch { exception ->
                        // Handle any exceptions by updating the screen state to error
                        _screenState.value = DetailListViewState.Error
                    }
                    .collect { data ->
                        // Process the result of getTodoListByUserId
                        if (data.isEmpty()) {
                            // If the data is empty, update the screen state to error
                            _screenState.value = DetailListViewState.Error
                        } else {
                            // If the data is not empty, update the screen state to success
                            _screenState.value = DetailListViewState.Success(data)
                        }
                    }
            }
        }

        // Function to handle changes in the checked state of a to-do item
        fun onCheckedItemChange(item: TodoListEntity) {
            viewModelScope.launch {
                // Update the to-do list item in the repository
                todoListRepository.updateTodoList(item)
            }
        }
    }
