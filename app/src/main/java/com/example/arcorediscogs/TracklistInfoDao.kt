
package com.example.arcorediscogs

import androidx.room.*

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

