package kijja.amityexam.todolist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kijja.amityexam.todolist.data.repository.TodoListRepository
import kijja.amityexam.todolist.data.repository.TodoListRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsTodoListRepository(todoListRepository: TodoListRepositoryImpl): TodoListRepository
}
