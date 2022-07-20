package com.example.myproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.R
import com.example.myproject.adapter.RVChildAdapter
import com.example.myproject.databinding.FragmentSubBreedFragmentBinding


class SubBreedFragment : Fragment(R.layout.fragment_sub_breed_fragment), RVChildAdapter.Listener {
    private lateinit var binding: FragmentSubBreedFragmentBinding
    private  var subBreedsList : ArrayList<String>? =null
    private  var breedName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSubBreedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments
        subBreedsList = data?.getStringArrayList("subBreeds")
        breedName = data?.getString("breedName")

        val str = breedName.toString().uppercase()
        binding.tvBreedName.text = "Sub-Breeds Of $str"

        binding.rvSub.layoutManager = LinearLayoutManager(this.context)
        binding.rvSub.adapter = RVChildAdapter(subBreedsList!!,this@SubBreedFragment)

    }

    override fun onItemClick(subBreedName: String) {
        val fragment = SubBreedImagesFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        bundle.putString("subBreedName",subBreedName) //sending chosen breed name to fragment from recyclerview
        bundle.putString("breedName",breedName)

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentBase,fragment)
            commit()
        }
    }


}