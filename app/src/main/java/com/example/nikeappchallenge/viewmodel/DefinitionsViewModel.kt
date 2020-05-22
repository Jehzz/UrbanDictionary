package com.example.nikeappchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.RepoCallback
import com.example.nikeappchallenge.model.repository.Repository

class DefinitionsViewModel() : ViewModel() {

    private val TAG = "ViewModel"

    //TODO: inject repository dependency
    private val repository = Repository()

    private var definitions = MutableLiveData<DescriptionList>()
    private val errorMessage = MutableLiveData<String>()
    private val isLoading = MutableLiveData<Boolean>()

    fun getUrbanDescription(): LiveData<DescriptionList> = definitions
    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getIsViewLoading(): LiveData<Boolean> = isLoading

    //TODO: add UI elements for error and loading events

    fun loadDefinitions(term: String) {
        isLoading.postValue(true)
        repository.retrieveDefinitions(term, object : RepoCallback<DescriptionList> {
            override fun onSuccess(data: DescriptionList) {
                definitions.value = data
                isLoading.postValue(false)
            }

            override fun onError(error: String?) {
                errorMessage.postValue(error)
                isLoading.postValue(false)
            }
        })
    }

    fun sortByDownVotes() {
        Log.d(TAG, "sortByDownVotes: ")
        definitions.value?.list?.sortedBy { it.thumbs_down }?.reversed()
            ?.apply { definitions.postValue(DescriptionList(this)) }
    }

    fun sortByUpVotes() {
        Log.d(TAG, "sortByUpVotes: ")
        definitions.value?.list?.sortedBy { it.thumbs_up }?.reversed()
            ?.apply { definitions.postValue(DescriptionList(this)) }
    }
}

class DefinitionsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DefinitionsViewModel() as T
    }
}