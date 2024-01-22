package kijja.amityexam.todolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kijja.amityexam.todolist.data.model.TodoListEntity

@Database(entities = [TodoListEntity::class], version = 1)
abstract class TodoListDatabase : RoomDatabase() {
    abstract fun todoListDao(): TodoListDao
}
