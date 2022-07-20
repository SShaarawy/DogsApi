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
        fun onItemClick(breedName: String, subBreeds: ArrayList<String>)
        fun onItemClick(breedName: String)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val key = dogList[position].keys.toString()
        val nKey = key.subSequence(1, key.length - 1).toString()
        val value = dogList[position].values.toString()
        val nValue = value.subSequence(2, value.length - 2).toString()
        val str = nKey.uppercase()
        val str2 = nValue.uppercase()

        var count = 0

        if (nValue.isNotEmpty()) {
            count++
            holder.itemView.findViewById<TextView>(R.id.tvName).text = "$str \n\nSub-Breeds Are\n\n{ ${str2} }"
        } else {
            count = 0
            holder.itemView.findViewById<TextView>(R.id.tvName).text = "$str "
        }

        holder.itemView.setOnClickListener {

            if (count > 0) {
                for (i in dogList[position].values) {
                    for (j in i) {
                        subBreeds.add(j)
                    }
                }
                for (i in 0 until subBreeds.size) {
                    println("subBreeds: " + subBreeds[i])
                }
                listener.onItemClick(nKey, subBreeds)
                subBreeds.clear()
            } else {
                listener.onItemClick(nKey)
            }
        }
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

}

