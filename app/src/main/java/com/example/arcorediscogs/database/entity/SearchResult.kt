package com.example.arcorediscogs.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL

@Entity
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val album : String,
    val artist: String,
    val genre: String,
    val released: String,
    val thumbnail: String){
    override fun toString() = "($id) $album $artist $genre $released $thumbnail"
}
