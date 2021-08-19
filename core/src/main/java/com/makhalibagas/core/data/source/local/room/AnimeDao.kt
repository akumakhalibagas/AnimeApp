package com.makhalibagas.core.data.source.local.room

import androidx.room.*
import com.makhalibagas.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(animes: List<AnimeEntity>)

    @Update(entity = AnimeEntity::class)
    fun updateAnime(anime: AnimeEntity)

    @Query("select * from tb_anime where isManga = 0")
    fun getAnimes(): Flow<List<AnimeEntity>>

    @Query("select * from tb_anime where isBookmark = 1 and isManga = 0")
    fun getBookmarkAnime(): Flow<List<AnimeEntity>>

    @Query("select * from tb_anime where isManga = 1")
    fun getManga(): Flow<List<AnimeEntity>>

    @Query("select * from tb_anime where isBookmark = 1 and isManga = 1")
    fun getBookmarkManga(): Flow<List<AnimeEntity>>
}