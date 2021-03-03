package com.example.arcorediscogs

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

object DiscogsApi {
    const val URL = "https://api.discogs.com/database/"

    object Model {
        data class Result(@SerializedName("results") val results: List<Results>)
        data class Results(@SerializedName("master_id") val master_id: Int)

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


object DiscogsApi2 {

    const val releaseURL = "https://api.discogs.com/"

    object Tracklist  {
        data class Result(@SerializedName("id") val id: Int)

    }
    interface Service {

        @GET("masters")
        suspend fun release(@Path("master_id") master_id: Int): Tracklist.Result
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(releaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(Service::class.java)
}