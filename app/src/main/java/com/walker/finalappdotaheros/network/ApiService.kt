package com.walker.finalappdotaheros.network

import com.walker.finalappdotaheros.models.Hero
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/heroStats/")
    suspend fun getHeroes(): Response<ArrayList<Hero>>

}