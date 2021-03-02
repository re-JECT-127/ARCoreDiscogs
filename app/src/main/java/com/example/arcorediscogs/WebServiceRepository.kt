package com.example.arcorediscogs

class WebServiceRepository() {

    private val call = DiscogsApi.service


    suspend fun getUser(totalHits: String) =
        call.artist("master", "$totalHits", "VfMCuKxEDTPpcrbKIpfmjhjEaGFntFhMXligFOol")
}