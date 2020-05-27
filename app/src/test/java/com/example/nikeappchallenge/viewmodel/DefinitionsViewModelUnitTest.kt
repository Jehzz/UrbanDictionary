package com.example.nikeappchallenge.viewmodel

import com.example.nikeappchallenge.model.repository.Repository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DefinitionsViewModelUnitTest{

    //Class under test
    private lateinit var viewModel: DefinitionsViewModel

    //@Mock
    //private lateinit var network: Network
    @Mock
    private lateinit var repo: Repository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
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