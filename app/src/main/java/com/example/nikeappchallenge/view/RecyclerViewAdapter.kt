package com.example.nikeappchallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeappchallenge.App.Companion.context
import com.example.nikeappchallenge.R
import com.example.nikeappchallenge.model.DescriptionList
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition
import kotlinx.android.synthetic.main.definition_item_layout.view.*

class RecyclerViewAdapter(val dataSet: DescriptionList, val clickListener: (UrbanDictionaryDefinition) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.definition_item_layout,
                    parent,
                    false
                )
        )

    override fun getItemCount(): Int = dataSet.list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(dataSet, position, clickListener)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: DescriptionList, position: Int, clickListener: (UrbanDictionaryDefinition) -> Unit) {
            //TODO: implement Databinding
            itemView.setOnClickListener { clickListener(data.list[position]) }

            itemView.tv_definition.text =  data.list[position].definition
            itemView.tv_upvotes.text = data.list[position].thumbs_up.toString()
            itemView.tv_downvotes.text = data.list[position].thumbs_down.toString()
            itemView.tv_author.text = context?.getString(R.string.Author) + data.list[position].author
        }
    }
}