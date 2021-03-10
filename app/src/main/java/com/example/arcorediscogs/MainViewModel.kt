package com.example.arcorediscogs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.arcorediscogs.api.WebServiceRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel: ViewModel() {
    private val repository: WebServiceRepository =
        WebServiceRepository()
    private val query = MutableLiveData<String>()
    private val tracklisQuery = MutableLiveData<Int>()
    private val barcodeQuery = MutableLiveData<String>()
     fun hitcountquery(name: String){
        Log.d("FYI", name)
        query.value = name

    }
    fun masterRelease(id: Int){
        tracklisQuery.value = id
    }
    fun barcodeScan(barcode: String){
        barcodeQuery.value = barcode
    }

    val totalHits = query.switchMap {
        liveData(Dispatchers.IO) {
            val retrievedHits = repository.getUser(it)
            emit(retrievedHits)
        }
    }
    val tracklist = tracklisQuery.switchMap {
        liveData(Dispatchers.IO) {
            val retrievedList = repository.getRelease(it)
            emit(retrievedList)
        }
    }
    val barcodeSearch = barcodeQuery.switchMap {
        liveData(Dispatchers.IO) {
            val retrievedList = repository.getBarcode(it)
            emit(retrievedList)
        }
    }
    }
