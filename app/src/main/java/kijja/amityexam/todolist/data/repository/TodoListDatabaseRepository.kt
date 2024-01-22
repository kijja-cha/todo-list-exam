package kijja.amityexam.todolist.data.repository

import kijja.amityexam.todolist.data.db.TodoListDao
import kijja.amityexam.todolist.data.model.TodoListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface TodoListDatabaseRepository {
    suspend fun isLoadedData(): Boolean

    fun getUserListFromRoom(): Flow<List<Int>>

    fun getTodoListByUserIdFromRoom(userId: Int): Flow<List<TodoListEntity>>

    suspend fun initTodoListDatabaseToRoom(list: List<TodoListEntity>)

    suspend fun addTodoListDatabaseToRoom(todoListEntity: TodoListEntity)

    suspend fun updateTodoListDatabaseInRoom(todoListEntity: TodoListEntity)

    suspend fun deleteTodoListDatabaseFromRoom(todoListEntity: TodoListEntity)
}

class TodoListDatabaseRepositoryImpl(
    private val todoListDao: TodoListDao,
) : TodoListDatabaseRepository {
    override suspend fun isLoadedData() = todoListDao.getUserList().first().isNotEmpty()

    override fun getUserListFromRoom() = todoListDao.getUserList()

    override fun getTodoListByUserIdFromRoom(userId: Int) = todoListDao.getTodoListByUserId(userId)

    override suspend fun initTodoListDatabaseToRoom(list: List<TodoListEntity>) = todoListDao.insertAllTodos(list)

    override suspend fun addTodoListDatabaseToRoom(todoListEntity: TodoListEntity) = todoListDao.insert(todoListEntity)

    override suspend fun updateTodoListDatabaseInRoom(todoListEntity: TodoListEntity) = todoListDao.update(todoListEntity)

    override suspend fun deleteTodoListDatabaseFromRoom(todoListEntity: TodoListEntity) = todoListDao.delete(todoListEntity)
}
