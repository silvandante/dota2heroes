package com.walker.finalappdotaheros.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://api.opendota.com"

object RetrofitInstance {
    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun createService(serviceClass: Class<ApiService>): ApiService {
        return retrofit.create(serviceClass)
    }
}