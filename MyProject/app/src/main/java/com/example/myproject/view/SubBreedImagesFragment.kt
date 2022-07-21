package com.example.myproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.adapter.RVImageAdapter
import com.example.myproject.model.DogsImage
import com.example.myproject.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubBreedImagesFragment : Fragment(),RVImageAdapter.Listener {
    private var breedName : String? = null
    private var subBreedName : String? = null
    private var rv : RecyclerView? = null
    private var subBreedImages = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = activity?.findViewById(R.id.rvImagesFragment)

        val data = arguments
        subBreedName = data?.getString("subBreedName")
        breedName = data?.getString("breedName")

        println("subBreedName $subBreedName")

        loadData()
    }

    private fun loadData() {

        val call = RetrofitInstance.api.getSubBreedImages(breedName, subBreedName)
        call.enqueue(object : Callback<DogsImage> {
            override fun onResponse(call: Call<DogsImage>, response: Response<DogsImage>) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        subBreedImages = ArrayList(it.message)
                    }

                    println("size: ${subBreedImages.size}")
                    println("images: ${response.body()?.message}")

                    rv?.apply {
                        layoutManager = GridLayoutManager(activity, 2)
                        adapter = RVImageAdapter(subBreedImages, this@SubBreedImagesFragment)
                    }
                }
            }
            override fun onFailure(call: Call<DogsImage>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onItemClick(breedName: String) {
        val fragment = ImageFullFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        bundle.putString("breedName",breedName) //sending chosen breed name to fragment from recyclerview

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentBase,fragment)
            commit()
        }
    }
}