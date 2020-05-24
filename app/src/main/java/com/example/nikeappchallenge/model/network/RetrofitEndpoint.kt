package com.example.nikeappchallenge.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.nikeappchallenge.App
import com.example.nikeappchallenge.BuildConfig
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import com.example.nikeappchallenge.model.utils.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitEndpoint {

    @GET("define")
    @Headers(Constants.HOST, Constants.API_KEY)
    fun getDefinition(@Query("term") input: String): Call<DescriptionList>

    //Companion object becomes singleton wrapper for Network client creation, setup, and network checking
    //No need to instantiate and build Network class in dependent class, simply call the methods through the interface
    companion object {
        fun initRetrofit(): RetrofitEndpoint {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RetrofitEndpoint::class.java)
        }

        val client: OkHttpClient by lazy {
            initClient()
        }

        private fun initClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            if (BuildConfig.DEBUG) {
                client.addInterceptor(logger)
            }
            client.addInterceptor {
                var request = it.request()
                request = if (offLineMode())
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=" + 1 * 60
                    ).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 2 * 60
                    ).build()
                it.proceed(request)
            }
                .cache(cacheConfig())
                .build()
            return client.build()
        }

        private fun cacheConfig(): Cache {
            val cacheSize = (5 * 1024 * 1024).toLong()
            return Cache(App.context!!.cacheDir, cacheSize)
        }


        private fun offLineMode(): Boolean {
            var isConnected: Boolean
            val connectiviyManager = App.context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            connectiviyManager
                .getNetworkCapabilities(connectiviyManager.activeNetwork)
                .apply {
                    isConnected = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            return isConnected
        }
    }
}