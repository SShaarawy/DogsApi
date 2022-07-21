package com.example.myproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R


class RVAdapter(
    private val dogList: ArrayList<Map<String, List<String>>>,
    private val listener: Listener,
) : RecyclerView.Adapter<RVAdapter.RowHolder>() {

    private val subBreeds = arrayListOf<String>()

    interface Listener {
        fun onItemClick(breedName: String, token: Int)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val key = dogList[position].keys.toString()
        val value = dogList[position].values.toString()

        val breedName = key.subSequence(1, key.length - 1).toString()
        val subBreedsNames = value.subSequence(2, value.length - 2).toString()

        val nKey = breedName.uppercase()
        val nValue = subBreedsNames.uppercase()

        var token = 0

        if (nValue.isNotEmpty()) {
            token++
            holder.itemView.findViewById<TextView>(R.id.tvName).text = "$nKey \n\nSub-Breeds Are\n\n{ $nValue }"
        } else {
            token = 0
            holder.itemView.findViewById<TextView>(R.id.tvName).text = nKey
        }

        holder.itemView.setOnClickListener {

            if (token > 0) {
                for (i in dogList[position].values) {
                    for (j in i) {
                        subBreeds.add(j)
                    }
                }
                for (i in 0 until subBreeds.size) {
                    println("subBreeds: " + subBreeds[i])
                }
                listener.onItemClick(breedName, token)
            } else {
                listener.onItemClick(breedName, token)
            }
        }
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

}

