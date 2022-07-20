package com.example.myproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myproject.R
import com.example.myproject.databinding.FragmentImageFullBinding
import com.squareup.picasso.Picasso

class ImageFullFragment : Fragment(R.layout.fragment_image_full) {
    private lateinit var binding : FragmentImageFullBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentImageFullBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments
        val breedImage = data?.getString("breedName").toString()
        Picasso.get().load(breedImage).into(binding.ivFull)

        println("fragmentImage: $breedImage")
    }

}