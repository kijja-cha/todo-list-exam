package kijja.amityexam.todolist.domain

import kijja.amityexam.todolist.data.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetUserListUseCase {
    fun getUserList(): Flow<Result<List<Int>>>
}

class GetUserListUseCaseImpl
    @Inject
    constructor(private val todoListRepository: TodoListRepository) : GetUserListUseCase {
        override fun getUserList(): Flow<Result<List<Int>>> {
            return todoListRepository.getTodo().map { response ->
                response.map { list ->
                    list.groupBy { it.userId ?: 0 }.keys.toList()
                }
            }
        }
    }
