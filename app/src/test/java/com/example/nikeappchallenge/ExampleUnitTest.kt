package com.example.nikeappchallenge

import android.util.Log
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.network.Network
import com.example.nikeappchallenge.model.network.RetrofitEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Thread.sleep

class ExampleUnitTest {

    //Class under test
    var network = Network()

    var testResponse: DescriptionList? = null

    @Test
    fun test_network() {
        network.initRetrofit().getDefinition("jess").enqueue(object :Callback<DescriptionList>{
            override fun onFailure(call: Call<DescriptionList>, t: Throwable) {
                fail()
            }
            override fun onResponse(call: Call<DescriptionList>, response: Response<DescriptionList>) {
                response.body()?.let {
                    if(response.isSuccessful){
                        testResponse = response.body()
                    }
                }
            }
        })

        //wait for valid response, check it exists
        sleep(1000)
        assertNotNull(testResponse)
    }
}