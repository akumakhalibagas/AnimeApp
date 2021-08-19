package com.makhalibagas.core.domain.usecase

import com.makhalibagas.core.data.Resource
import com.makhalibagas.core.domain.model.Anime
import com.makhalibagas.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeInteractor @Inject constructor(private val animeRepository: IAnimeRepository) :
    AnimeUseCase {

    override fun getAnime(): Flow<Resource<List<Anime>>> = animeRepository.getAnimes()
    override fun getBookmark(): Flow<List<Anime>> = animeRepository.getBookmarkAnime()
    override fun setBookmark(anime: Anime, newStatus: Boolean) =
        animeRepository.setBookmark(anime, newStatus)

    override fun getManga(): Flow<Resource<List<Anime>>> = animeRepository.getManga()
    override fun getBookmarkManga(): Flow<List<Anime>> = animeRepository.getBookmarkManga()

}