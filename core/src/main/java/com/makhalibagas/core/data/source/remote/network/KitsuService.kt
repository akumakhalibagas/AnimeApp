package com.makhalibagas.core.data.source.remote.network

import com.makhalibagas.core.data.source.remote.response.KitsuResponse
import retrofit2.http.GET

interface KitsuService {

    @GET("/api/edge/anime")
    suspend fun getAnimes(): KitsuResponse

    @GET("/api/edge/manga")
    suspend fun getMangas(): KitsuResponse

}