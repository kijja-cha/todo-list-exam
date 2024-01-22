package kijja.amityexam.todolist.data.repository

import kijja.amityexam.todolist.data.db.TodoListDao
import kijja.amityexam.todolist.data.model.TodoListEntity
import javax.inject.Inject

interface TodoListDatabaseRepository {
    suspend fun isLoadedData(): Boolean

    suspend fun getUserListFromRoom(): List<Int>

    suspend fun getTodoListByUserIdFromRoom(userId: Int): List<TodoListEntity>

    suspend fun initTodoListDatabaseToRoom(list: List<TodoListEntity>)

    suspend fun addTodoListDatabaseToRoom(todoListEntity: TodoListEntity)

    suspend fun updateTodoListDatabaseInRoom(todoListEntity: TodoListEntity)

    suspend fun deleteTodoListDatabaseFromRoom(todoListEntity: TodoListEntity)
}

class TodoListDatabaseRepositoryImpl
    @Inject
    constructor(
        private val todoListDao: TodoListDao,
    ) : TodoListDatabaseRepository {
        override suspend fun isLoadedData() = todoListDao.getUserList().isNotEmpty()

        override suspend fun getUserListFromRoom() = todoListDao.getUserList()

        override suspend fun getTodoListByUserIdFromRoom(userId: Int) = todoListDao.getTodoListByUserId(userId)

        override suspend fun initTodoListDatabaseToRoom(list: List<TodoListEntity>) = todoListDao.insertAllTodos(list)

        override suspend fun addTodoListDatabaseToRoom(todoListEntity: TodoListEntity) = todoListDao.insert(todoListEntity)

        override suspend fun updateTodoListDatabaseInRoom(todoListEntity: TodoListEntity) = todoListDao.update(todoListEntity)

        override suspend fun deleteTodoListDatabaseFromRoom(todoListEntity: TodoListEntity) = todoListDao.delete(todoListEntity)
    }
