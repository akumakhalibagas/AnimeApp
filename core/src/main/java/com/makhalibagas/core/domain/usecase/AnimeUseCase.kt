package com.makhalibagas.core.domain.usecase

import com.makhalibagas.core.data.Resource
import com.makhalibagas.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {

    fun getAnime(): Flow<Resource<List<Anime>>>
    fun getBookmark(): Flow<List<Anime>>
    fun setBookmark(anime: Anime, newStatus: Boolean)
    fun getManga(): Flow<Resource<List<Anime>>>
    fun getBookmarkManga(): Flow<List<Anime>>
}