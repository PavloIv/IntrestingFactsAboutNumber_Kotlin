package com.example.intrestingfactsaboutnumber_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intrestingfactsaboutnumber_kotlin.repository.ApiRepository
import com.example.intrestingfactsaboutnumber_kotlin.response.FactResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {
    val loading = MutableLiveData<Boolean>()

    val getRandomFact = MutableLiveData<FactResponse>()
    val getNumberFact = MutableLiveData<FactResponse>()

    fun loadRandomFact() = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.getRandomFact()
        if (response.isSuccessful) {
            getRandomFact.postValue(response.body())
        }
        loading.postValue(false)
    }

    fun loadNumberFact(number: Int) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.getNumberFacts(number)
        if (response.isSuccessful){
            getNumberFact.postValue(response.body())
        }
        loading.postValue(false)
    }
}