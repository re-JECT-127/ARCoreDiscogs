
package com.example.arcorediscogs


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SearchResultViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView)

 class SearchResultAdapter(private val items: List<Result>?) :
    RecyclerView.Adapter<SearchResultViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup,
                                     viewType: Int) = SearchResultViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_view,
            parent, false))

     override fun getItemCount() = items?.size ?: 0

     override fun onBindViewHolder(holder: SearchResultViewHolder,
                                    position: Int) {
       //  holder.itemView.textView.text =
       // items?.get(position).toString()
         holder.itemView.setOnClickListener {
             //... e.g. update user details fragment
               }
         }
     }

