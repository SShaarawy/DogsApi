package com.example.myproject.service

import com.example.myproject.model.Breeds
import com.example.myproject.model.DogsImage
import com.example.myproject.model.SubBreeds
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {

    @GET("breeds/list/all")
    fun getBreeds() : Call<Breeds>

    @GET("breed/{breedName}/images")
    fun getBreedImages(@Path("breedName") breedName: String?): Call<DogsImage>

    @GET("breed/{breedName}/list")
    fun getSubBreeds(@Path("breedName") breedName: String?): Call<SubBreeds>

    @GET("breed/{breedName}/{subBreedName}/images")
    fun getSubBreedImages(@Path("breedName") breedName: String?, @Path("subBreedName") subBreedName: String?): Call<DogsImage>


}