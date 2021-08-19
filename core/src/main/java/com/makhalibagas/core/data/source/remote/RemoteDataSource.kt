package com.makhalibagas.core.data.source.remote

import com.makhalibagas.core.data.source.remote.network.KitsuService
import com.makhalibagas.core.data.source.remote.response.DataItem
import com.makhalibagas.core.data.source.remote.vo.KitsuApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val kitsuService: KitsuService) {

    suspend fun getAnimes(): Flow<KitsuApiResponse<List<DataItem>>> {
        return flow {

            try {
                val result = kitsuService.getAnimes()
                val animes = result.data
                if (animes.isNotEmpty()) {
                    emit(KitsuApiResponse.Success(result.data))
                } else {
                    emit(KitsuApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(KitsuApiResponse.Error(e.toString()))
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMangas(): Flow<KitsuApiResponse<List<DataItem>>> {
        return flow {

            try {
                val result = kitsuService.getMangas()
                val animes = result.data
                if (animes.isNotEmpty()) {
                    emit(KitsuApiResponse.Success(result.data))
                } else {
                    emit(KitsuApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(KitsuApiResponse.Error(e.toString()))
            }

        }.flowOn(Dispatchers.IO)
    }
}