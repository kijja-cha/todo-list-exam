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
    fun getTodo(): Flow<Result<List<TodoListEntity>>>
}

class TodoListRepositoryImpl
    @Inject
    constructor(private val httpClient: HttpClient) : TodoListRepository {
        override fun getTodo(): Flow<Result<List<TodoListEntity>>> {
            return flow {
                val response = httpClient.get("/todos")
                if (response.status.isSuccess()) {
                    emit(Result.success(response.body()))
                } else {
                    emit(Result.failure(IllegalStateException()))
                }
            }
        }
    }
