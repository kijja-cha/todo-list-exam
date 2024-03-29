package kijja.amityexam.todolist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kijja.amityexam.todolist.data.model.TodoListEntity

@Dao
interface TodoListDao {
    @Query("SELECT * FROM todos WHERE userId = :userId")
    suspend fun getTodoListByUserId(userId: Int): List<TodoListEntity>

    @Query("SELECT userId FROM todos GROUP BY userId")
    suspend fun getUserList(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTodos(list: List<TodoListEntity>)

    @Update
    suspend fun update(todo: TodoListEntity)
}
