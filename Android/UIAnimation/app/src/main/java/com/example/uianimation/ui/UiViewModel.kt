package com.example.uianimation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import kotlin.random.Random

class UiViewModel : ViewModel() {
    private val _buttonText = MutableLiveData<String>("Initial")
    val buttonText: LiveData<String>
        get() = _buttonText.map { "mapped $it" }



    fun changeText() {
        _buttonText.value = Random.nextInt(1000).toString()
        Log.d("MYTAG", "livedatahash - ${buttonText.hashCode()}")
        Log.d("MYTAG", "mutablelivedatahash - ${buttonText.hashCode()}")
    }
}