package com.example.myproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myproject.R

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val breedName = intent.getStringExtra("breedName")
        val subBreeds = intent.getStringArrayListExtra("subBreeds")

        if (subBreeds == null) {
            val fragment = ImagesFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            bundle.putString("breedName", breedName) //sending chosen breed name to fragment from recyclerview

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentBase, fragment)
                commit()
            }
        } else {
            val fragment = SubBreedFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            bundle.putStringArrayList("subBreeds", subBreeds) //sending chosen sub breed list to fragment from recyclerview
            bundle.putString("breedName",breedName) //sending chosen breed name to fragment from recyclerview

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentBase, fragment)
                commit()
            }
        }


    }
}