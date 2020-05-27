package com.example.nikeappchallenge.model.repository

import android.util.Log
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository : IRepo {

    private val TAG = "Repository"

    override fun retrieveDefinitions(term: String, repoCallback: RepoCallback<DescriptionList>) {

        Network().initRetrofit().getDefinition(term)
            .enqueue(object : Callback<DescriptionList> {
                override fun onFailure(call: Call<DescriptionList>, t: Throwable) {
                    repoCallback.onError(t.message)
                }

                override fun onResponse(
                    call: Call<DescriptionList>,
                    response: Response<DescriptionList>
                ) {
                    response.body()?.let {
                        if (response.isSuccessful) {
                            Log.v(TAG, "data ${it}")
                            repoCallback.onSuccess(it)
                        } else {
                            repoCallback.onError("error")
                        }
                    }
                }
            })
    }
}