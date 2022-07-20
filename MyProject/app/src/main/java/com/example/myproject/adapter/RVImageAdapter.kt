package com.example.myproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.squareup.picasso.Picasso


class RVImageAdapter(
    private val dogsImages: ArrayList<String>,
    private val listener: Listener
    ) : RecyclerView.Adapter<RVImageAdapter.RowHolder>(){

    private lateinit var imageView: ImageView

    interface Listener {
        fun onItemClick(breedName: String)
    }

    class RowHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_images_item,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.itemView.apply {
            imageView = findViewById(R.id.iv)
            setOnClickListener{
                val str = dogsImages[position]
                listener.onItemClick(str)
            }
        }
        Picasso.get().load(dogsImages[position]).into(imageView)
    }

    override fun getItemCount(): Int {
        return dogsImages.size
    }

}