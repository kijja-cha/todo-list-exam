package kijja.amityexam.todolist.ui.screen.user

import androidx.annotation.VisibleForTesting
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
class UserListViewModel
    @Inject
    constructor(private val todoListRepository: TodoListRepository) : ViewModel() {
        // MutableStateFlow to represent the screen state of the user list
        private val _screenState = MutableStateFlow<UserListViewState>(UserListViewState.Loading)
        val screenState = _screenState.asStateFlow()

        // Flag to track whether the data has been initialized
        private var isInitData = false

        // Function to initialize data loading, ensuring it happens only once
        fun initLoadData() {
            if (isInitData) return
            isInitData = true

            // Trigger the data loading process
            loadData()
        }

        // Function to load user list data asynchronously
        @VisibleForTesting
        fun loadData() {
            viewModelScope.launch {
                // Execute the getUserListUseCase in the IO dispatcher
                todoListRepository.getUserList()
                    .flowOn(Dispatchers.IO)
                    .catch {
                        // Handle any exceptions by updating the screen state to error
                        _screenState.value = UserListViewState.Error
                    }
                    .collect { data ->
                        // Process the result of getUserListUseCase
                        // If the data is not empty, update the screen state to success
                        if (data.isNotEmpty()) {
                            _screenState.value = UserListViewState.Success(data)
                        } else {
                            // If the data is empty, update the screen state to error
                            _screenState.value = UserListViewState.Error
                        }
                    }
            }
        }
    }
