package com.example.myproject.model

data class Breeds (
    val message: Map<String,List<String>>,
    val status : String
    )

data class DogsImage (
    val message: List<String>,
    val status : String
        )

data class SubBreeds(
    val message: List<String>,
    val status : String
)


