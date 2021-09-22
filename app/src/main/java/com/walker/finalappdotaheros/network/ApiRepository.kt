package com.walker.finalappdotaheros.network

import com.walker.finalappdotaheros.utils.Resource
import retrofit2.Response

class ApiRepository (
    private val apiService: ApiService
) {
    suspend fun getHeroes() = getResponse {
        apiService.getHeroes()
    }

    private suspend fun <T> getResponse(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) Resource.success(body)
                else Resource.error("Erro!")
            } else {
                Resource.error("Erro")
            }
        } catch(e: Exception) {
            Resource.error(e.message)
        }
    }
}
