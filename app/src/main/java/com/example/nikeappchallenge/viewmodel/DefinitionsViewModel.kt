package com.example.nikeappchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import com.example.nikeappchallenge.model.network.RetrofitEndpoint
import com.example.nikeappchallenge.model.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DefinitionsViewModel(): ViewModel() {

    private val TAG = "ViewModel"

    //TODO: inject repository dependency
    private val repository = Repository()

    private var definitions = MutableLiveData<DescriptionList>()
    private val errorMessage = MutableLiveData<String>()

    fun getUrbanDescription(): LiveData<DescriptionList> = definitions
    fun getErrorMessage(): LiveData<String> = errorMessage

    //TODO: add progress indicator on loading new term definitions
    fun loadDefinitions(term: String) {
        repository.makeNewQuery(term)
    }


    fun sortDefinitionsAscending() {
        TODO("sorting not yet implemented")
    }

    fun sortDefinitionsDescending() {
        TODO("sorting not yet implemented")
    }
}

class DefinitionsViewModelFactory : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DefinitionsViewModel() as T
    }
}