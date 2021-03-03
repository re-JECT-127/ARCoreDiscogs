

package com.example.arcorediscogs

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultDao {
    @Query("SELECT * FROM result")
    fun getAll(): LiveData<List<Result>>

    @Query("SELECT * FROM result WHERE result.id = :resultid")
    fun getAlbumTrackInfo(resultid: Int): AlbumTrackInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: Result): Long

    @Update
    fun update(result: Result)

    @Delete
    fun delete(result: Result)
}


