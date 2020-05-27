package com.example.nikeappchallenge

import android.content.Context
import com.example.nikeappchallenge.model.network.DescriptionList
import com.example.nikeappchallenge.model.network.Network
import com.example.nikeappchallenge.model.network.RetrofitEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Thread.sleep

class EndpointTest {

    @Test
    fun test_network() {

        var result : DescriptionList? = null
        var resultCode: Int? = null
        //Network.kt requires app context, so manually create client and retrofit here
        //This test only proves the Endpoint @GET methods work
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RetrofitEndpoint::class.java)

        retrofit.getDefinition("test")
            .enqueue(object : Callback<DescriptionList> {
                override fun onFailure(call: Call<DescriptionList>, t: Throwable) {
                    fail()
                }

                override fun onResponse(
                    call: Call<DescriptionList>,
                    response: Response<DescriptionList>
                ) {
                    resultCode = response.code()
                    result = response.body()
                }

            })
        //wait for and check response
        sleep(1000) //TODO: replace hard coded wait with synchronous wait
        assertEquals(200, resultCode)
        assertNotNull(result)
    }
}