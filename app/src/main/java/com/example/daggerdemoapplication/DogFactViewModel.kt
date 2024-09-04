package com.example.daggerdemoapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DogFactViewModel @Inject constructor(private val repository: DogFactRepository): ViewModel() {
    private val _dogFacts = MutableLiveData<DogFact>()
    val dogFacts: LiveData<DogFact> get()= _dogFacts
    fun fetchDogFacts(number: Int){
        repository.getDogFacts(number){
            if (it != null){
                Log.d("Doggo", dogFacts.toString())
                _dogFacts.postValue(it)
            }
        }
    }
}