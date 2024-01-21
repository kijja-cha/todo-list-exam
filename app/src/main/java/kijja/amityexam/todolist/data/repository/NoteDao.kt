package kijja.amityexam.todolist.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kijja.amityexam.todolist.data.model.TodoListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(todo: TodoListEntity)

    @Update
    suspend fun update(todo: TodoListEntity)

    @Query("SELECT * FROM todos")
    fun getAllTodoList(): Flow<List<TodoListEntity>>
}
