/*
package com.example.arcorediscogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CardFragment : Fragment(R.layout.fragment_card) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv_results = getView()?.findViewById<RecyclerView>(R.id.rv_results)
        val ump = ViewModelProvider(this).get(ResultModel::class.java)
        ump.getResults().observe(viewLifecycleOwner, {
            rv_results?.adapter = SearchResultAdapter(it?.sortedBy { that ->
                that. })
        })
    }
    }
*/
