package com.makhalibagas.core.data.source.remote.vo

sealed class KitsuApiResponse<out R> {
    data class Success<out T>(val data: T) : KitsuApiResponse<T>()
    data class Error(val errorMessage: String) : KitsuApiResponse<Nothing>()
    object Empty : KitsuApiResponse<Nothing>()
}