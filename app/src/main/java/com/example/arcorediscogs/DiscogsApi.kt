package com.example.arcorediscogs

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object DiscogsApi {
    const val URL = "https://api.discogs.com/artists/1/releases?page=2&per_page=75"
    object Model {
        data class presidentHits(val totalHits: String?)
    }
    interface Service {
        @GET("releases")
        suspend fun artist(@Query("page") action: String): Model.presidentHits
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(Service::class.java)
}