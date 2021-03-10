package com.example.arcorediscogs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arcorediscogs.database.ResultDB
import com.example.arcorediscogs.database.ResultModel
import com.example.arcorediscogs.database.TrackListModelFactory
import com.example.arcorediscogs.database.TracklistInfoModel
import com.example.arcorediscogs.database.entity.Result
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.recent_searches_item.*


class RecentSearches(var id: Long = 0) : Fragment() {
    private lateinit var recentFragmentView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        Log.d("FYI", "Test")
        recentFragmentView = inflater.inflate(R.layout.fragment_recent_searches, container, false)


        return recentFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("FYI", "Test2 $id")
        val recentViewMod = ViewModelProvider(this).get(ResultModel::class.java)
        recentViewMod.getResults().observe(viewLifecycleOwner, {
            Log.d("FYI", "recent Adapter $it")
            rv_results.layoutManager = LinearLayoutManager(requireContext())
            rv_results.adapter = RecentSearchAdapter(it)
        })


        val rv_resultsrv = view?.findViewById<RecyclerView>(R.id.rv_resultsrv)
        val trackListModel: TracklistInfoModel by viewModels {
            TrackListModelFactory(
                this.requireActivity().application,
                id
            )
        }
        Log.d("FYI", "Test2 $id")
        val viewModrv = ViewModelProvider(this).get(trackListModel::class.java)
        viewModrv.getTracks().observe(viewLifecycleOwner, {
            Log.d("FYI", "Adapter $it")
            if (rv_resultsrv != null) {
                rv_resultsrv.layoutManager = LinearLayoutManager(requireContext())
            }
            if (rv_resultsrv != null) {
                rv_resultsrv.adapter = RecentRvAdapter(it)
            }
        })

    }

}
