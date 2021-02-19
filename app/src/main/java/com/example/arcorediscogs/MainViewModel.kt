package com.example.arcorediscogs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.Dispatchers

class MainViewModel: ViewModel() {
    private val repository: WebServiceRepository = WebServiceRepository()
    private val query = MutableLiveData<String>()

     fun hitcountquery(name: String){
        Log.d("FYI", name)
        query.value = name

    }

    val totalHits = query.switchMap {
        liveData(Dispatchers.IO) {
            val retrievedHits = repository.getUser(it)
            emit(retrievedHits)
        }
    }
}