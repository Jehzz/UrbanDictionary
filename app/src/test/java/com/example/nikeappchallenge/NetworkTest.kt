package com.example.nikeappchallenge

import com.example.nikeappchallenge.model.network.DescriptionList
import com.example.nikeappchallenge.model.network.Network

import org.junit.Test
import org.junit.Assert.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep

class NetworkTest {

    //TODO: update test or App.kt, usage of App.context for cacheDir causes fail: context uninitialized

    var network = Network()

    //Test variables
    var responseCode: Int = 0
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
                        responseCode = response.code()
                        testResponse = response.body()
                    }
                }
            }
        })

        //wait for and check response
        sleep(1000) //TODO: replace hard coded wait with synchronous wait
        assertEquals(responseCode, 200) //might be redundant
        assertNotNull(testResponse)
    }
}