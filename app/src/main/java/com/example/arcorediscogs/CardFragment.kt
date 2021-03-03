package com.example.arcorediscogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders


class CardFragment : Fragment(R.layout.fragment_card) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ump = ViewModelProviders.of(this).get(ResultModel::class.java)
         ump.getResults().observe(this, {
             rv_results.adapter = SearchResultAdapter(it?.sortedBy { that ->
                that.lastname })
            })
    }
}