package com.example.arcorediscogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var title: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imgTrackingFragment = ImageTrackingFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment2, imgTrackingFragment)
            commit()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.totalHits.observe(this, Observer {
            Log.d("FYI", it.results[0].toString())
            Log.d("FYI", viewModel.totalHits.toString())
            // viewModel.hitcountquery(name = it.artist.toString())
        })

    }

        fun search() {
            Log.d("FYI", "toimiiko")
            viewModel.hitcountquery("nirvana-nevermind")

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            Log.d("mAct", "Fab clicked")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment2, ImageTrackingFragment())
                commit()
            }
        }

        val bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)

        bottomAppBar.setNavigationOnClickListener {
            Log.d("mAct", "Nav clicked")
            // Handle navigation icon press
        }

        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    Log.d("mAct", "Search clicked")
                    // Handle search icon press
                    true
                }

                else -> false
            }
        }
    }
}