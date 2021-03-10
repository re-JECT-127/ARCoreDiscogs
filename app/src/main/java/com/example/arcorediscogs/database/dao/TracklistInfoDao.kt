package com.example.arcorediscogs.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.arcorediscogs.database.entity.TracklistInfo

@Dao
interface TracklistInfoDao {
    @Query("SELECT * FROM tracklistinfo WHERE master_id = :resultid")
    fun getAll(resultid: Long): LiveData<List<TracklistInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tracklistInfo: TracklistInfo)

    @Update
    fun update(tracklistInfo: TracklistInfo)

    @Delete
    fun delete(tracklistInfo: TracklistInfo)
}

