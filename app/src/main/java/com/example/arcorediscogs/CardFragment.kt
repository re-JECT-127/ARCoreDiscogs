
package com.example.arcorediscogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CardFragment : Fragment() {
    private val tracks: List<Result>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_card, container, false)

        val album = view?.findViewById<TextView>(R.id.textView5)

        album?.text = tracks?.get(0)?.album
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            val rv_results = view?.findViewById<RecyclerView>(R.id.rv_results)
        rv_results?.layoutManager = LinearLayoutManager(requireContext())
        rv_results?.adapter = SearchResultAdapter()
        }
    }


