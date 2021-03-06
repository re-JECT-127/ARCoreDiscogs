package com.example.arcorediscogs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var spinner: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Defining the loading spinner

        spinner = findViewById(R.id.progressBar1)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment2, mainFrag())
            commit()
            spinner.visibility = View.GONE
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.totalHits.observe(this, Observer {
            Log.d("FYI", it.results[0].toString())
            Log.d("FYI", viewModel.totalHits.toString())
            // viewModel.hitcountquery(name = it.artist.toString())
        })
        //FAB Functionality
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            //Making the spinner run while the ImageTrackingFragment loads
            spinner.visibility = View.VISIBLE
            Log.d("mAct", "Fab clicked")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment2, ImageTrackingFragment(), "1").addToBackStack("bkstk")
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
                    search()
                    true
                }

                else -> false
            }
        }
    }

    fun search() {
        Log.d("FYI", "toimiiko")
    }

    //Phoning home safely from ImageTrackingFragment
    fun returnFromImgFrag(id: Long) {
        supportFragmentManager.beginTransaction().apply {
            remove(supportFragmentManager.findFragmentByTag("1")!!)
            replace(R.id.fragment2, CardFragment(id))
            Log.d("FYI", "Tried to load Fragment $id")
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
            spinner.visibility = View.GONE
        } else {
            super.onBackPressed()
            spinner.visibility = View.GONE
        }
    }
}

