package com.example.nikeappchallenge.model.repository

import com.example.nikeappchallenge.model.network.DescriptionList

interface RepoCallback<T> {
    fun onSuccess(data: DescriptionList)
    fun onError(error:String?)
}