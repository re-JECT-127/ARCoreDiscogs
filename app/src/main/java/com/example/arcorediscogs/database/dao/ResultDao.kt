package com.example.arcorediscogs.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.arcorediscogs.database.AlbumTrackInfo
import com.example.arcorediscogs.database.entity.Result

@Dao
interface ResultDao {
    @Query("SELECT * FROM result WHERE result.id = :id")
    fun getAll(id: Long): LiveData<List<Result>>

    @Query("SELECT * FROM result WHERE result.id = :resultid")
    fun getAlbumTrackInfo(resultid: Int): AlbumTrackInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: Result): Long

    @Update
    fun update(result: Result)

    @Delete
    fun delete(result: Result)
}


