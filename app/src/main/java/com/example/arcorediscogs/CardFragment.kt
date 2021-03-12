package com.example.arcorediscogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arcorediscogs.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card.*


class CardFragment(var id: Long = 0) : Fragment() {

    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        Log.d("FYI", "Test")
        fragmentView = inflater.inflate(R.layout.fragment_card, container, false)
        val albumtext = fragmentView.findViewById<TextView>(R.id.album)
        val artist = fragmentView.findViewById<TextView>(R.id.textView6)
        val genre = fragmentView.findViewById<TextView>(R.id.textView7)
        val released = fragmentView.findViewById<TextView>(R.id.textView8)
        val albumart = fragmentView.findViewById<ImageView>(R.id.imageView2)
        val picasso = Picasso.get()
        val resultModel: ResultModel by viewModels {
            ResultModelFactory(
                this.requireActivity().application,
                id
            )
        }
        val viewMod = ViewModelProvider(this).get(resultModel::class.java)
        viewMod.getResults().observe(viewLifecycleOwner) {
            albumtext?.text = it[0].album
            artist?.text = it[0].artist
            genre?.text = it[0].genre
            released?.text = it[0].released

            picasso.load(it[0].thumbnail).into(albumart)


            Log.d("FYI", "Albumtext $albumart")
            Log.d("FYI", "Albumtext $albumtext")
            Log.d("FYI", "Albumtext ${it[0].album}")
        }
        return fragmentView
    }//.resultDao().getAll()

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
        viewMod.getTracks().observe(viewLifecycleOwner) {
            Log.d("FYI", "Adapter $it")
            rv_results.layoutManager = LinearLayoutManager(requireContext())
            rv_results.adapter = SearchResultAdapter(it)
        }
        /*val rv_results = view?.findViewById<RecyclerView>(R.id.rv_results)
    rv_results?.layoutManager = LinearLayoutManager(requireContext())
    rv_results?.adapter = SearchResultAdapter()*/
    }
}


