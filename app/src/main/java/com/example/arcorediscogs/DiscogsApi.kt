package com.example.arcorediscogs

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object DiscogsApi {
    const val URL = "https://api.discogs.com/database/"
    object Model {
        data class Result(@SerializedName("results") val results: List<Results>)
        data class Results(@SerializedName("thumb") val thumb: String)
    }
    interface Service {
        @GET("search")
        suspend fun artist(@Query("type") master: String, @Query("title") title: String, @Query("token") token: String): Model.Result
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(Service::class.java)
}