package kijja.amityexam.todolist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kijja.amityexam.todolist.data.model.TodoListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {
    @Query("SELECT * FROM todos WHERE userId = :userId")
    fun getTodoListByUserId(userId: Int): Flow<List<TodoListEntity>>

    @Query("SELECT userId FROM todos GROUP BY userId")
    fun getUserList(): Flow<List<Int>>

    @Insert
    suspend fun insert(todo: TodoListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTodos(list: List<TodoListEntity>)

    @Update
    suspend fun update(todo: TodoListEntity)

    @Delete
    suspend fun delete(todo: TodoListEntity)
}
