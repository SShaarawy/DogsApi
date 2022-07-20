package com.example.myproject.service

import com.example.myproject.model.Dog
import com.example.myproject.model.DogsImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {

    @GET("breeds/list/all")
    fun getData() : Call<Dog>

    @GET("breed/{breedName}/images")
    fun getBreedImages(@Path("breedName") breedName: String?): Call<DogsImage>

    @GET("breed/{breedName}/{subBreedName}/images")
    fun getSubBreedImages(@Path("breedName") breedName: String?, @Path("subBreedName") subBreedName: String?): Call<DogsImage>


}