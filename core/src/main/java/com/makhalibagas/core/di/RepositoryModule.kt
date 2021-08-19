package com.makhalibagas.core.di

import com.makhalibagas.core.data.AnimeRepository
import com.makhalibagas.core.domain.repository.IAnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(animeRepository: AnimeRepository): IAnimeRepository

}