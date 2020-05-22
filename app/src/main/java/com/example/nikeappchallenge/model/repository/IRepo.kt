package com.example.nikeappchallenge.model.repository

import com.example.nikeappchallenge.model.RepoCallback
import com.example.nikeappchallenge.model.DescriptionList


interface IRepo {

    fun retrieveDefinitions(term: String, repoCallback: RepoCallback<DescriptionList>)
    fun cancel()
}