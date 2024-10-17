package com.example.fetchtakehometest

import com.example.fetchtakehometest.repository.ItemRepository
import com.example.fetchtakehometest.repository.ItemRepositoryImpl
import com.example.fetchtakehometest.usecase.ItemUseCaseImpl
import com.example.fetchtakehometest.usecase.ItemUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindGetHiringUseCase( getHiringUseCaseImpl: ItemUseCaseImpl): ItemUsecase

    @Binds
    abstract fun bindHiringRepository(hiringRepositoryImpl: ItemRepositoryImpl): ItemRepository

}