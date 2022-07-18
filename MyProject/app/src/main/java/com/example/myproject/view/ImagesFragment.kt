package com.example.myproject.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myproject.R
import com.example.myproject.adapter.RecyclerViewImageAdapter
import com.example.myproject.databinding.FragmentImagesBinding
import com.example.myproject.model.DogsImage
import com.example.myproject.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesFragment : Fragment(R.layout.fragment_images),RecyclerViewImageAdapter.Listener {
    private lateinit var binding : FragmentImagesBinding
    private var breedName : String? = null
    private var dogsImages = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments
        breedName = data?.getString("breedName").toString()

        println("fragment: $breedName")

        loadData()
    }

    private fun loadData(){

        val call = RetrofitInstance.api.getBreedImages(breedName)
        call.enqueue(object : Callback<DogsImage> {
            override fun onResponse(call: Call<DogsImage>, response: Response<DogsImage>) {

                if(response.isSuccessful){
                    response.body()?.let {
                        dogsImages = ArrayList(it.message)
                    }

                    println("size: ${dogsImages.size}")
                    println("images: ${response.body()?.message}")

                    binding.rvImagesFragment.apply {
                        layoutManager = GridLayoutManager(activity,2)
                        adapter = RecyclerViewImageAdapter(dogsImages,this@ImagesFragment)
                    }
                }
            }

            override fun onFailure(call: Call<DogsImage>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(breedImage: String) {
        val fragment = ImageFullFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        bundle.putString("breedImage",breedImage) //sending chosen breed name to fragment from recyclerview

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentBase,fragment)
            commit()
        }
    }

}