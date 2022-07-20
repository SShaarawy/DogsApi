package com.example.myproject.view



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.adapter.RVAdapter

import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.model.Dog

import com.example.myproject.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),RVAdapter.Listener {

    private lateinit var binding : ActivityMainBinding
    private var dogList = ArrayList<Map<String,List<String>>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()
    }

    private fun loadData() {

        val call = RetrofitInstance.api.getData()
        call.enqueue(object: Callback<Dog>{
            override fun onResponse(
                call: Call<Dog>,
                response: Response<Dog>,
            ) {
                if(response.isSuccessful){
                    println("name:${response.body()?.message?.entries}")
                    println("member "+response.body()?.message!!.keys)
                    println("member "+response.body()?.message!!)

                    for(entry in response.body()?.message!!){
                        dogList.add(mapOf(entry.key to entry.value))
                    }
                    println("size "+dogList.size)

                    binding.rv.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = RVAdapter(dogList,this@MainActivity)
                    }
                }
            }
            override fun onFailure(call: Call<Dog>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(breedName: String) {
        val intent = Intent(this,DisplayActivity::class.java)
        intent.putExtra("breedName",breedName)
        startActivity(intent)

    }

    override fun onItemClick(breedName: String, subBreeds: ArrayList<String>) {
        val intent = Intent(this,DisplayActivity::class.java)
        intent.putExtra("breedName",breedName)
        intent.putExtra("subBreeds",subBreeds)
        startActivity(intent)

    }

}