package com.makhalibagas.animeaja.di

import com.makhalibagas.core.domain.usecase.AnimeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BookmarkModuleDependencies {

    fun animeUseCase(): AnimeUseCase

}