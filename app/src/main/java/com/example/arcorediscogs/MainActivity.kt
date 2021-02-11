package com.example.arcorediscogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomappbar.BottomAppBar

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bla = findViewById<BottomAppBar>(R.id.bottomAppBar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.totalHits.observe(this, Observer {
            Log.d("FYI", it.items.toString())
            // viewModel.hitcountquery(name = it.artist.toString())
        })
    }
    fun search(view: View){
        Log.d("FYI", "toimiiko")
        viewModel.hitcountquery("rock")

    }
}