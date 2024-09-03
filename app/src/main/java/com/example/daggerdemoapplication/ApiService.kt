package com.example.daggerdemoapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/facts")
    fun getDogFacts(@Query("number") number:Int): Call<DogFact>
}