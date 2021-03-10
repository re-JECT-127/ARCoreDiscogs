package com.example.arcorediscogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arcorediscogs.database.ResultDB
import com.example.arcorediscogs.database.TrackListModelFactory
import com.example.arcorediscogs.database.TracklistInfoModel
import com.example.arcorediscogs.database.entity.Result
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.recent_searches_item.*


class CardFragment(var id: Long = 0) : Fragment() {

    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        Log.d("FYI", "Test")
        fragmentView = inflater.inflate(R.layout.fragment_card, container, false)
        val db by lazy { ResultDB.get(fragmentView.context) }
        val albumresult: List<Result>?
        val albumtext = view?.findViewById<TextView>(R.id.album)
        val artist = view?.findViewById<TextView>(R.id.textView6)
        val genre = view?.findViewById<TextView>(R.id.textView7)
        val released = view?.findViewById<TextView>(R.id.textView8)
        albumtext?.text = db.resultDao().getAll().value?.get(id.toInt())?.album
        artist?.text = db.resultDao().getAll().value?.get(id.toInt())?.artist
        genre?.text = db.resultDao().getAll().value?.get(id.toInt())?.genre
        released?.text = db.resultDao().getAll().value?.get(id.toInt())?.released

        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val trackListModel: TracklistInfoModel by viewModels {
            TrackListModelFactory(
                this.requireActivity().application,
                id
            )
        }
        Log.d("FYI", "Test2 $id")
        val viewMod = ViewModelProvider(this).get(trackListModel::class.java)
        viewMod.getTracks().observe(viewLifecycleOwner, {
            Log.d("FYI", "Adapter $it")
            rv_resultsrv.layoutManager = LinearLayoutManager(requireContext())
            rv_resultsrv.adapter = SearchResultAdapter(it)
        })
        /*val rv_results = view?.findViewById<RecyclerView>(R.id.rv_results)
    rv_results?.layoutManager = LinearLayoutManager(requireContext())
    rv_results?.adapter = SearchResultAdapter()*/
    }
}


