package com.example.arcorediscogs

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(
    entity = Result::class,
    onDelete = ForeignKey.CASCADE,
    parentColumns = ["id"],
    childColumns = ["result"]))])
data class TracklistInfo(
    val album: Long,
    val trackNumb: String,
    val song: String,
    val duration: String,
    val mixer: String,
    val mixerAssistant: String,
    val producer: String,
    val vocalsAdditional: String,
    val writer: String,
    @PrimaryKey
    val value: String
)