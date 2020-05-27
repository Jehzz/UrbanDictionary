package com.example.nikeappchallenge

import android.app.Application
import android.content.Context
import okhttp3.Cache
import java.io.File

//This class allows accessing the App's context anywhere
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}