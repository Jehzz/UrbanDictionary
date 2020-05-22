package com.example.nikeappchallenge.model

interface RepoCallback<T> {
    fun onSuccess(data: DescriptionList)
    fun onError(error:String?)
}