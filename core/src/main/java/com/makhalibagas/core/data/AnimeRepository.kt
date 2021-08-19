package com.makhalibagas.core.data

import com.makhalibagas.core.data.source.local.LocalDataSource
import com.makhalibagas.core.data.source.remote.RemoteDataSource
import com.makhalibagas.core.data.source.remote.response.DataItem
import com.makhalibagas.core.data.source.remote.vo.KitsuApiResponse
import com.makhalibagas.core.domain.model.Anime
import com.makhalibagas.core.domain.repository.IAnimeRepository
import com.makhalibagas.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IAnimeRepository {

    override fun setBookmark(anime: Anime, newStatus: Boolean) {
        val animeEntity = DataMapper.mapDomainToEntities(anime)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setBookmark(animeEntity, newStatus)
        }
    }

    override fun getAnimes(): Flow<Resource<List<Anime>>> {
        return object :
            NetworkBoundResource<List<Anime>, List<DataItem>>() {
            override fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getAnimes().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Anime>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<KitsuApiResponse<List<DataItem>>> =
                remoteDataSource.getAnimes()

            override suspend fun saveCallResult(data: List<DataItem>) {
                val animes = DataMapper.mapResponseToEntities(data)
                localDataSource.insertAnime(animes)
            }

        }.asFlow()

    }

    override fun getBookmarkAnime(): Flow<List<Anime>> {
        return localDataSource.getBookmarkAnime().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getManga(): Flow<Resource<List<Anime>>> {
        return object :
            NetworkBoundResource<List<Anime>, List<DataItem>>() {
            override fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getManga().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Anime>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<KitsuApiResponse<List<DataItem>>> =
                remoteDataSource.getMangas()

            override suspend fun saveCallResult(data: List<DataItem>) {
                val animes = DataMapper.mapResponseMangaToEntities(data)
                localDataSource.insertAnime(animes)
            }

        }.asFlow()
    }

    override fun getBookmarkManga(): Flow<List<Anime>> {
        return localDataSource.getBookmarkManga().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

}