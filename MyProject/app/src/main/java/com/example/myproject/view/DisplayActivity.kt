package com.example.myproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myproject.R

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val breedName = intent.getStringExtra("breedName")

        val fragment = ImagesFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        bundle.putString("breedName",breedName) //sending chosen breed name to fragment from recyclerview

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentBase,fragment)
            commit()
        }
    }
}