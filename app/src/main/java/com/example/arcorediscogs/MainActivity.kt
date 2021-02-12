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
        val imgTrackingFragment = ImgTrackingFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment2, imgTrackingFragment)
            commit()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.totalHits.observe(this, Observer {
            Log.d("FYI", it.pagination.items.toString())
            // viewModel.hitcountquery(name = it.artist.toString())
        })
    }
    fun search(view: View){
        Log.d("FYI", "toimiiko")
        viewModel.hitcountquery("rock")



        val bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)

        bottomAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }

                else -> false
            }
        }
    }
}