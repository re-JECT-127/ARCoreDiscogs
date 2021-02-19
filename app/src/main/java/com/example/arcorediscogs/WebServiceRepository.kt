package com.example.arcorediscogs

class WebServiceRepository() {

    lateinit var stinkler: stinkler
    private val call = DiscogsApi.service


    suspend fun getUser(totalHits: String) =
        call.artist("query", "$totalHits", "VfMCuKxEDTPpcrbKIpfmjhjEaGFntFhMXligFOol")
}