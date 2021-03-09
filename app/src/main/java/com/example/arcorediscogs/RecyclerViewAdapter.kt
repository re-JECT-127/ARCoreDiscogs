
package com.example.arcorediscogs


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arcorediscogs.database.entity.TracklistInfo

class SearchResultViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView)

 class SearchResultAdapter(private val items: List<TracklistInfo>?) : RecyclerView.Adapter<SearchResultViewHolder>() {
//     private var items: List<TracklistInfo>? = null
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchResultViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_view,
            parent, false))

     override fun getItemCount() = items?.size ?: 0

     override fun onBindViewHolder(holder: SearchResultViewHolder,
                                    position: Int) {
         val textView: TextView = holder.itemView.findViewById(R.id.textView2)
         textView.text = items?.get(position).toString()
         val textView2: TextView = holder.itemView.findViewById(R.id.textView3)
         textView2.text = items?.get(position).toString()
         val textView3: TextView = holder.itemView.findViewById(R.id.textView4)
         textView3.text = items?.get(position).toString()



     }
     }

