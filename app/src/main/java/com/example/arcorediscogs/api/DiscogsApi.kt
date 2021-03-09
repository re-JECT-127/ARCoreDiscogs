package com.example.arcorediscogs.api

import android.nfc.tech.NfcBarcode
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object DiscogsApi {
    const val URL = "https://api.discogs.com/"

    object Model {
        data class Result(@SerializedName("results") val results: List<Results>)
        data class Results(
            @SerializedName("master_id") val master_id: String,
            @SerializedName("title") val title: String,
            @SerializedName("genre") val genre: List<String>,
            @SerializedName("year") val year: String,
            @SerializedName("thumb") val thumb: String
        )
    }

    interface Service {
        @GET("database/search")
        suspend fun artist(
            @Query("type") master: String,
            @Query("title") title: String,
            @Query("token") token: String
        ): Model.Result

    }

    interface Service2 {
        @GET("masters/{master_id}")
        suspend fun release(@Path("master_id") master_id: Int): Tracklist.Result
    }

    object Tracklist {
        data class Result(@SerializedName("tracklist") val tracklist: List<Tracklistt>)
        data class Tracklistt(
            @SerializedName("position") val position: String,
            @SerializedName("title") val title: String,
            @SerializedName("duration") val duration: String
        )


    }

    interface Service3 {
        @GET("search")
        suspend fun artist(
            @Query("type") master: String,
            @Query("barcode") barcode: NfcBarcode,
            @Query("token") token: String
        ): Model.Result

    }


    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(
        Service::class.java)
    val service2 = retrofit.create(
        Service2::class.java)
}
