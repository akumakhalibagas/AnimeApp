package com.makhalibagas.core.data.source.local

import com.makhalibagas.core.data.source.local.entity.AnimeEntity
import com.makhalibagas.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val animeDao: AnimeDao) {

    suspend fun insertAnime(animes: List<AnimeEntity>) = animeDao.insertAnime(animes)

    fun setBookmark(anime: AnimeEntity, newStatus: Boolean) {
        anime.isBookmark = newStatus
        animeDao.updateAnime(anime)
    }

    fun getAnimes(): Flow<List<AnimeEntity>> = animeDao.getAnimes()

    fun getBookmarkAnime(): Flow<List<AnimeEntity>> = animeDao.getBookmarkAnime()

    fun getManga(): Flow<List<AnimeEntity>> = animeDao.getManga()

    fun getBookmarkManga(): Flow<List<AnimeEntity>> = animeDao.getBookmarkManga()


}