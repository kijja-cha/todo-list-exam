package kijja.amityexam.todolist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kijja.amityexam.todolist.domain.GetDetailListUseCase
import kijja.amityexam.todolist.domain.GetDetailListUseCaseImpl
import kijja.amityexam.todolist.domain.GetUserListUseCase
import kijja.amityexam.todolist.domain.GetUserListUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindsGetUserListUseCase(getUserListUseCase: GetUserListUseCaseImpl): GetUserListUseCase

    @Binds
    fun bindsGetDetailListUseCase(getDetailListUseCase: GetDetailListUseCaseImpl): GetDetailListUseCase
}
