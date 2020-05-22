package com.example.nikeappchallenge.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.network.RetrofitEndpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository:IRepo{

    private val TAG = "Repository"

    private val errorMessage = MutableLiveData<String>()
    fun getError(): LiveData<String>? = errorMessage

    override fun retrieveDefinitions(term: String, repoCallback: com.example.nikeappchallenge.model.RepoCallback<DescriptionList>) {
        RetrofitEndpoint.initRetrofit().getDefinition(term).enqueue(object :Callback<DescriptionList>{
            override fun onFailure(call: Call<DescriptionList>, t: Throwable) {
                repoCallback.onError(t.message)
            }

            override fun onResponse(call: Call<DescriptionList>, response: Response<DescriptionList>) {
                response.body()?.let {
                    if(response.isSuccessful){
                        Log.v(TAG, "data ${it}")
                        repoCallback.onSuccess(it)
                    }else{
                        repoCallback.onError("error")
                    }
                }
            }
        })
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}