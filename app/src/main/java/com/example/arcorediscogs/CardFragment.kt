
package com.example.arcorediscogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arcorediscogs.database.TracklistInfoModel
import com.example.arcorediscogs.database.entity.Result
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.arcorediscogs.database.TrackListModelFactory
import kotlinx.android.synthetic.main.fragment_card.*


class CardFragment(val id: Long) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentView: View
    private val tracks: List<Result>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentView = inflater.inflate(R.layout.fragment_card, container, false)

        val album = view?.findViewById<TextView>(R.id.textView5)

        album?.text = tracks?.get(0)?.album
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         val trackListModel: TracklistInfoModel by viewModels{TrackListModelFactory(this.requireActivity().application, id)}

         val viewMod = ViewModelProviders.of(this).get(TracklistInfoModel::class.java)
        viewMod.getTracks().observe(viewLifecycleOwner, {rv_results.adapter = SearchResultAdapter()})
            /*val rv_results = view?.findViewById<RecyclerView>(R.id.rv_results)
        rv_results?.layoutManager = LinearLayoutManager(requireContext())
        rv_results?.adapter = SearchResultAdapter()*/
        }
    }


