package com.example.nikeappchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeappchallenge.R
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import com.example.nikeappchallenge.viewmodel.DefinitionsViewModel
import com.example.nikeappchallenge.viewmodel.DefinitionsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity(), IClickDefinition {

    private val TAG = "MainActivity"

    private lateinit var definitionsViewModel: DefinitionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        observeViewModel()
        setTextChangedListener()
    }

    private fun setupViewModel() {
        definitionsViewModel = ViewModelProvider(this, DefinitionsViewModelFactory())
            .get(DefinitionsViewModel::class.java)
    }

    private fun observeViewModel() {
        definitionsViewModel.getUrbanDescription()
            .observe(this, Observer<DescriptionList> { t ->
                rv_search_results.layoutManager = LinearLayoutManager(
                    this@MainActivity
                )
                t?.let {
                    rv_search_results.adapter =
                        RecyclerViewAdapter(
                            t
                        ) { urbanDefinition: UrbanDictionaryDefinition -> onClick(urbanDefinition) }
                }
            })
    }

    /*TextWatcher with timer delay prevents flooding the network with API calls on each
    keyboard stroke, only responds when user is done typing.
    */
    private fun setTextChangedListener() {
        et_term_search.addTextChangedListener(object : TextWatcher {
            var timer = Timer()
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        GlobalScope.launch {
                            definitionsViewModel.loadDefinitions(s.toString())
                        }
                    }
                }, 600)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer.cancel()
            }
        })
    }

    override fun onClick(urbanDefinition: UrbanDictionaryDefinition) {
        //Any future behavior that depends on a selected definition can be triggered here
    }
}