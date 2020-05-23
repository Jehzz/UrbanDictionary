package com.example.nikeappchallenge.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.nikeappchallenge.model.repository.Repository
import com.example.nikeappchallenge.viewmodel.DefinitionsViewModel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ViewModelTest{

    //Class under test
    lateinit var viewModel: DefinitionsViewModel

    //Testing and Mockito setup
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = DefinitionsViewModel(repository)
    }

    //TODO: tests

}