package com.example.daggerdemoapplication

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DogFactRepository @Inject constructor(private val apiService: ApiService) {

    fun getDogFacts(number: Int, callback: (DogFact?) -> Unit){
        apiService.getDogFacts(number).enqueue(object: Callback<DogFact> {
            override fun onResponse(call: Call<DogFact>, response: Response<DogFact>) {
                if (response.isSuccessful){
                    Log.d("Doggo", response.body().toString())
                    callback(response.body())
                } else {
                    Log.d("Doggo", "Could not fetch dog facts. Response says not successful")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<DogFact>, t: Throwable) {
                Log.d("Doggo", "Could not fetch dog facts due to failure")
                callback(null)
            }

        })
    }
}