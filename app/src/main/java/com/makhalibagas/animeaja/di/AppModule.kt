package com.makhalibagas.animeaja.di

import com.makhalibagas.core.domain.usecase.AnimeInteractor
import com.makhalibagas.core.domain.usecase.AnimeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//https://stackoverflow.com/questions/65988186/error-cannot-find-symbol-dagger-hilt-installinvalue-applicationcomponent-c

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideAnimeRepository(animeInteractor: AnimeInteractor): AnimeUseCase

}