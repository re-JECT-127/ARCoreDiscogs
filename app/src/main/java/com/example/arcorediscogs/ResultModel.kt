package com.example.arcorediscogs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class ResultModel(application: Application): AndroidViewModel(application) {
    private val results: LiveData<List<Result>> = ResultDB.get(getApplication()).resultDao().getAll()

    fun getResults() = results


}
