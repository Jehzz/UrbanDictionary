package com.example.nikeappchallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeappchallenge.R
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition

class RecyclerViewAdapter(val dataSet: List<UrbanDictionaryDefinition>, val clickListener: (UrbanDictionaryDefinition) -> Unit) :
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

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(dataSet, position, clickListener)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: List<UrbanDictionaryDefinition>, position: Int, clickListener: (UrbanDictionaryDefinition) -> Unit) {

            itemView.setOnClickListener { clickListener(data[position]) }
        }
    }
}