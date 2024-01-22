package kijja.amityexam.todolist.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kijja.amityexam.todolist.data.model.TodoListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TodoListRepository {
    fun getUserList(): Flow<List<Int>>

    fun getTodoListByUserId(userId: Int): Flow<List<TodoListEntity>>
}

class TodoListRepositoryImpl
    @Inject
    constructor(
        private val httpClient: HttpClient,
        private val databaseRepository: TodoListDatabaseRepositoryImpl,
    ) : TodoListRepository {
        override fun getUserList(): Flow<List<Int>> {
            return flow {
                if (databaseRepository.isLoadedData()) {
                    emit(databaseRepository.getUserListFromRoom())
                }
                val response = httpClient.get("/todos")
                if (response.status.isSuccess()) {
                    databaseRepository.initTodoListDatabaseToRoom(response.body())
                    emit(databaseRepository.getUserListFromRoom())
                } else {
                    emit(listOf())
                }
            }
        }

        override fun getTodoListByUserId(userId: Int): Flow<List<TodoListEntity>> {
            return flow {
                emit(databaseRepository.getTodoListByUserIdFromRoom(userId))
            }
        }
    }
