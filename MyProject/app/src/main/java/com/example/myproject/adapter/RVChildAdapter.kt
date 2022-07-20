package com.example.myproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R

class RVChildAdapter(private val subBreeds: ArrayList<String>, private val listener: Listener) : RecyclerView.Adapter<RVChildAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(subBreedName: String)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("sizes: ${subBreeds.size} ")
        holder.itemView.findViewById<TextView>(R.id.tvSubName).text = subBreeds[position]
        holder.itemView.setOnClickListener {
            listener.onItemClick(subBreeds[position])
        }

    }

    override fun getItemCount(): Int {
        return subBreeds.size
    }
}