package kijja.amityexam.todolist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kijja.amityexam.todolist.data.db.TodoListDao
import kijja.amityexam.todolist.data.db.TodoListDatabase
import kijja.amityexam.todolist.data.repository.TodoListDatabaseRepository
import kijja.amityexam.todolist.data.repository.TodoListDatabaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideTodoListDatabase(
        @ApplicationContext
        context: Context,
    ) = Room.databaseBuilder(
        context,
        TodoListDatabase::class.java,
        "todos",
    ).build()

    @Provides
    fun provideTodoListDao(todoListDatabase: TodoListDatabase) = todoListDatabase.todoListDao()

    @Provides
    fun provideTodoListDatabaseRepository(todoListDao: TodoListDao): TodoListDatabaseRepository =
        TodoListDatabaseRepositoryImpl(
            todoListDao = todoListDao,
        )
}
