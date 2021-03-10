package com.example.arcorediscogs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arcorediscogs.database.entity.Result
import com.example.arcorediscogs.database.entity.TracklistInfo

class RecentSearchViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView)

class RecentSearchAdapter(private val items: List<Result>?) : RecyclerView.Adapter<RecentSearchViewHolder>() {
    //     private var items: List<TracklistInfo>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecentSearchViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recent_searches_item,
            parent, false))


    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: RecentSearchViewHolder,
                                  position: Int) {
        Log.d("FYI", "Itemlist $items")
        val textView: TextView = holder.itemView.findViewById(R.id.ralbum)
        textView.text = items?.get(position)?.album
        val textView2: TextView = holder.itemView.findViewById(R.id.rartist)
        textView2.text = items?.get(position)?.artist
        val textView3: TextView = holder.itemView.findViewById(R.id.rgenre)
        textView3.text = items?.get(position)?.genre
        val textView4: TextView = holder.itemView.findViewById(R.id.rreleased)
        textView4.text = items?.get(position)?.released


    }
}

