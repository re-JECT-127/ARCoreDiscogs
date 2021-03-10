package com.example.arcorediscogs.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arcorediscogs.database.entity.Result
import com.example.arcorediscogs.database.entity.TracklistInfo


class ResultModel(application: Application) : AndroidViewModel(application) {
    private val results: LiveData<List<Result>> =
        ResultDB.get(getApplication()).resultDao().getAll()

    fun getResults() = results

}

class TracklistInfoModel(application: Application, id: Long) : AndroidViewModel(application) {
    private val tracks: LiveData<List<TracklistInfo>> =
        ResultDB.get(getApplication()).tracklistInfoDao().getAll(id)

    fun getTracks() = tracks
}

class TrackListModelFactory(private val application: Application, private val id: Long) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TracklistInfoModel(application, id) as T


}
