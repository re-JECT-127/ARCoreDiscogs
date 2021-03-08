
package com.example.arcorediscogs

import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@androidx.room.Entity(foreignKeys = [(ForeignKey(
    entity = Result::class,
    onDelete = CASCADE,
    parentColumns = ["id"],
    childColumns = ["master_id"]))])
data class TracklistInfo(
    val master_id: Int,
    val album: String,
    val trackNumb: String,
    val song: String,
    val duration: String,
    @PrimaryKey
    val value: String
)
