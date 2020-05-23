package com.example.nikeappchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeappchallenge.R
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import com.example.nikeappchallenge.model.repository.Repository
import com.example.nikeappchallenge.viewmodel.DefinitionsViewModel
import com.example.nikeappchallenge.viewmodel.DefinitionsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IClickDefinition {


    private val TAG = "MainActivity"

    private lateinit var definitionsViewModel: DefinitionsViewModel

    private val repository by lazy { Repository() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObservables()
    }

    private fun setupViewModel() {

        definitionsViewModel = ViewModelProvider(this, DefinitionsViewModelFactory())
            .get(DefinitionsViewModel::class.java)

    }

    private fun setupObservables() {

        //Observe Definitions response
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


        //Observe loading status
        definitionsViewModel.getIsViewLoading()
            .observe(this, Observer{ t->
                when(t){
                    true -> progressBar.visibility = View.VISIBLE
                    false-> progressBar.visibility = View.GONE
                }
            })

        //Observe error response
        definitionsViewModel.getErrorMessage()
            .observe(this, Observer{ t->
                Toast.makeText(this@MainActivity, t, Toast.LENGTH_LONG).show()
            })
    }

    override fun onClick(urbanDefinition: UrbanDictionaryDefinition) {
        //Any future behavior that depends on a selected definition can be triggered here
        //example: launching a fragment view of the definition showing more information, or allowing
        //the user to vote it up or down if the API allows
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.sort_menu, menu)

        val searchItem = menu?.findItem(R.id.mi_searchView)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                definitionsViewModel.loadDefinitions(query.toString())
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.mi_sort_downvotes -> {
            definitionsViewModel.sortByDownVotes()
            true
        }
        R.id.mi_sort_upvotes -> {
            definitionsViewModel.sortByUpVotes()
            true
        }
        else -> {
            //Unrecognized input
            super.onOptionsItemSelected(item)
        }
    }
}