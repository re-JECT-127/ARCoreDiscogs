package com.example.arcorediscogs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.arcorediscogs.database.dao.ResultDao
import com.example.arcorediscogs.database.dao.TracklistInfoDao
import com.example.arcorediscogs.database.entity.Result
import com.example.arcorediscogs.database.entity.TracklistInfo

@Database(entities = [(Result::class), (TracklistInfo::class)], version = 1)
abstract class ResultDB : RoomDatabase() {
    abstract fun resultDao(): ResultDao
    abstract fun tracklistInfoDao(): TracklistInfoDao

    companion object {
        private var sInstance: ResultDB? = null

        @Synchronized
        fun get(context: Context): ResultDB {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDB::class.java, "result.db"
                ).build()
            }
            return sInstance!!
        }
    }
}

