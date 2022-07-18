package com.example.myproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R


class RecyclerViewAdapter(private val dogList: ArrayList<Map<String,List<String>>>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(){

    interface Listener {
        fun onItemClick(breedName: String)
    }

    class RowHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return RowHolder(view)
    }


    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val key = dogList[position].keys.toString()
        val nKey = key.subSequence(1,key.length-1).toString()
        val value = dogList[position].values.toString()
        //println(value)
        val nValue = value.subSequence(2,value.length-2).toString()
        //println(nValue)

        holder.itemView.apply{
           // findViewById<TextView>(R.id.tvName).text = dogList[position].entries.toString()
            findViewById<TextView>(R.id.tvName).text = "## $nKey ##"

            if(nValue.isNotEmpty()){
                findViewById<TextView>(R.id.tvName).text = "## $nKey ##\n\nsub-breeds are\n\n${nValue}"
            }

            setOnClickListener {
                listener.onItemClick(nKey)
            }
        }
    }
    override fun getItemCount(): Int {
        return dogList.size
    }

}