package com.example.arcorediscogs.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.arcorediscogs.database.entity.Result


class ResultModel(application: Application): AndroidViewModel(application) {
    private val results: LiveData<List<Result>> = ResultDB.get(getApplication()).resultDao().getAll()


    fun getResults() = results


}
