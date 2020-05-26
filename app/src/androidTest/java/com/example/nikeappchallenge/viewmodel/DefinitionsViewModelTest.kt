package com.example.nikeappchallenge.viewmodel

import com.example.nikeappchallenge.model.network.Network
import com.example.nikeappchallenge.model.repository.Repository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock

class DefinitionsViewModelTest {

    //Class under test
    private lateinit var viewModel: DefinitionsViewModel

    @Mock
    private lateinit var network: Network
    @Mock
    private lateinit var repo: Repository

    @Before
    fun setup(){

    }


    @Test
    fun loadDefinitions() {
    }

    @Test
    fun sortByDownVotes() {
    }

    @Test
    fun sortByUpVotes() {
    }
}