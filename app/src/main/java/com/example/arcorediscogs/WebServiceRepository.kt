package com.example.arcorediscogs

class WebServiceRepository(var title: String) {

    private val call = DiscogsApi.service
    suspend fun getUser(jtn: String) = call.artist("query", "$title", "VfMCuKxEDTPpcrbKIpfmjhjEaGFntFhMXligFOol")
}