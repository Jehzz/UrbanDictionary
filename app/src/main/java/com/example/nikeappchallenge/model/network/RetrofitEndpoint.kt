package com.example.nikeappchallenge.model.network

import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitEndpoint {

    @GET("define")
    @Headers(Constants.HOST, Constants.API_KEY)
    fun getDefinition(@Query("term") input: String): Call<DescriptionList>

}