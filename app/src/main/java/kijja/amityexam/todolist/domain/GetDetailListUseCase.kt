package kijja.amityexam.todolist.domain

import kijja.amityexam.todolist.data.model.TodoListEntity
import kijja.amityexam.todolist.data.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetDetailListUseCase {
    fun getDetailListByUser(userId: Int): Flow<Result<List<TodoListEntity>>>
}

class GetDetailListUseCaseImpl
    @Inject
    constructor(private val todoListRepository: TodoListRepository) : GetDetailListUseCase {
        override fun getDetailListByUser(userId: Int): Flow<Result<List<TodoListEntity>>> {
            return todoListRepository.getTodo().map { response ->
                response.map { list ->
                    list.filter { it.userId == userId }
                }
            }
        }
    }
