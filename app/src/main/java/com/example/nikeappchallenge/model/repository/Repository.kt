package com.example.nikeappchallenge.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition

class Repository {

    //dataset
    private val allDefinitions = MutableLiveData<List<UrbanDictionaryDefinition>>()
    fun getAllDefinitions(): LiveData<List<UrbanDictionaryDefinition>> {
        return allDefinitions
    }

    //Error message
    private val errorMessage = MutableLiveData<String>()
    fun getError(): LiveData<String>? {
        return errorMessage
    }

    fun loadDefinitionsFromNetwork(){
        TODO("invoke Network class here, override onResponse and onFailure, set data and error messages accordingly")
    }

}