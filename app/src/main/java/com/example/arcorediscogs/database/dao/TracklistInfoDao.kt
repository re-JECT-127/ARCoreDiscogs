
package com.example.arcorediscogs.database.dao

import androidx.room.*
import com.example.arcorediscogs.database.entity.TracklistInfo

@Dao
interface TracklistInfoDao {
    @Query("SELECT * FROM tracklistinfo WHERE album = :resultid")
    fun getAll(resultid: Long): List<TracklistInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tracklistInfo: TracklistInfo)

    @Update
    fun update(tracklistInfo: TracklistInfo)

    @Delete
    fun delete(tracklistInfo: TracklistInfo)
}

