package com.example.intrestingfactsaboutnumber_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intrestingfactsaboutnumber_kotlin.db.FactsEntity
import com.example.intrestingfactsaboutnumber_kotlin.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {

    private val _factsList = MutableLiveData<List<FactsEntity>>()
    val factsList : LiveData<List<FactsEntity>>
        get() = _factsList

    init {
        getAllFacts()
    }

    fun saveFact(entity: FactsEntity) = viewModelScope.launch {
        repository.saveFact(entity)

    }

    fun getAllFacts() = viewModelScope.launch {
        repository.getAllFacts()
            .collect { _factsList.postValue(it) }
    }
}