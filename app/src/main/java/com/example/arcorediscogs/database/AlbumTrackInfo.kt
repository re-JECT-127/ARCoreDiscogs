package com.example.arcorediscogs.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.arcorediscogs.database.entity.Result
import com.example.arcorediscogs.database.entity.TracklistInfo

class AlbumTrackInfo {
    @Embedded
    var result: Result? = null

    @Relation(parentColumn = "id", entityColumn = "master_id")
    var contacts: List<TracklistInfo>? = null
}
