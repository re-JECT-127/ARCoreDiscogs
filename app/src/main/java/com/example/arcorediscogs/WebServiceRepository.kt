package com.example.arcorediscogs

class WebServiceRepository() {

    private val call = DiscogsApi.service
    private val call2 = DiscogsApi.service2

    suspend fun getUser(totalHits: String) =
        call.artist("master", "$totalHits", "VfMCuKxEDTPpcrbKIpfmjhjEaGFntFhMXligFOol")
    suspend fun getRelease(id: Int) =
        call2.release(id)
}