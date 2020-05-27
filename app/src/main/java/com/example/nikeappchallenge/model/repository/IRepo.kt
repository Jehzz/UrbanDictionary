package com.example.nikeappchallenge.model.repository

import com.example.nikeappchallenge.model.network.DescriptionList

interface IRepo {
    fun retrieveDefinitions(term: String, repoCallback: RepoCallback<DescriptionList>)
}