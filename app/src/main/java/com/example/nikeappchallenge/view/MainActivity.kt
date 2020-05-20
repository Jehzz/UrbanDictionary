package com.example.nikeappchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikeappchallenge.R
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import com.example.nikeappchallenge.viewmodel.DefinitionsViewModel

class MainActivity : AppCompatActivity(), IClickDefinition {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var definitionsViewModel: DefinitionsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewmodel()
    }

    private fun setupViewmodel() {
        TODO("instantiate ViewModel and adapter, setup recyclerview")
    }

    fun setSearchListener(){
        TODO("implement textlistener on a SearchView, pass search term to viewmodel for fetching/sorting")
    }

    override fun onClick(definition: UrbanDictionaryDefinition) {
        TODO("Not yet implemented")
    }
}