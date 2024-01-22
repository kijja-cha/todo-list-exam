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

    suspend fun updateTodoList(item: TodoListEntity)
}

class TodoListRepositoryImpl
    @Inject
    constructor(
        private val httpClient: HttpClient,
        private val databaseRepository: TodoListDatabaseRepositoryImpl,
    ) : TodoListRepository {
        // Retrieve the user list, either from Room database or by fetching from the network
        override fun getUserList(): Flow<List<Int>> {
            return flow {
                // Check if data is already loaded in the Room database
                if (databaseRepository.isLoadedData()) {
                    // Emit the user list from Room
                    emit(databaseRepository.getUserListFromRoom())
                } else {
                    // Fetch user list from the network using HttpClient
                    val response = httpClient.get("/todos")

                    // Check if the network request was successful
                    if (response.status.isSuccess()) {
                        // Initialize the Room database with the fetched data
                        databaseRepository.initTodoListDatabaseToRoom(response.body())

                        // Emit the user list from Room
                        emit(databaseRepository.getUserListFromRoom())
                    } else {
                        // Emit an empty list if there was an error fetching data from the network
                        emit(emptyList())
                    }
                }
            }
        }

        // Retrieve the to-do list for a specific user from the Room database
        override fun getTodoListByUserId(userId: Int): Flow<List<TodoListEntity>> {
            return flow {
                // Emit the to-do list for the specified user from Room
                emit(databaseRepository.getTodoListByUserIdFromRoom(userId))
            }
        }

        // Update a todo list item in the Room database
        override suspend fun updateTodoList(item: TodoListEntity) {
            databaseRepository.updateTodoListDatabaseInRoom(item)
        }
    }
