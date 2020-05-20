package com.example.nikeappchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import com.example.nikeappchallenge.model.repository.Repository

class DefinitionsViewModel: ViewModel(){

    private val definitions = MutableLiveData<List<UrbanDictionaryDefinition>>()

    // TODO: 5/20/20
    //Might not even use a Repo?
    //Move Repo instantiation to lateinit/by lazy for easier testing?
    private val repo: Repository = Repository()

    fun getDefinitions(): LiveData<List<UrbanDictionaryDefinition>> = definitions

    fun loadDefinitions(){
        TODO("either utilize Network here and load, or fetch from the repository")
    }

    fun sortDefinitionsAscending(){
        TODO("sorting not yet implemented")
    }
    fun sortDefinitionsDescending(){
        TODO("sorting not yet implemented")
    }

}