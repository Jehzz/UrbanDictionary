package com.example.nikeappchallenge.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import com.example.nikeappchallenge.model.network.RetrofitEndpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val TAG = "Repository"

    private val allDefinitions = MutableLiveData<DescriptionList>()
    private val errorMessage = MutableLiveData<String>()

    fun getError(): LiveData<String>? = errorMessage
    fun getAllDefinitions(): LiveData<DescriptionList> = allDefinitions

    fun makeNewQuery(term: String){
        Log.d(TAG, "makeNewQuery: ")
        RetrofitEndpoint.initRetrofit().getDefinition(term)
            .enqueue(object : Callback<DescriptionList> {

                override fun onFailure(call: Call<DescriptionList>, t: Throwable) {
                    Log.d(TAG, "onFailure: " +t.stackTrace)
                }
                override fun onResponse(
                    call: Call<DescriptionList>,
                    response: Response<DescriptionList>
                ) {
                    allDefinitions.postValue(response.body())
                }
            })
    }
}