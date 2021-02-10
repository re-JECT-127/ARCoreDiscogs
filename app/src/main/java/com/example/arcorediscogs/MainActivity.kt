package com.example.arcorediscogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomappbar.BottomAppBar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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