package com.makhalibagas.core.domain.repository

import com.makhalibagas.core.data.Resource
import com.makhalibagas.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {

    fun setBookmark(anime: Anime, newStatus: Boolean)

    fun getAnimes(): Flow<Resource<List<Anime>>>

    fun getBookmarkAnime(): Flow<List<Anime>>

    fun getManga(): Flow<Resource<List<Anime>>>

    fun getBookmarkManga(): Flow<List<Anime>>

}