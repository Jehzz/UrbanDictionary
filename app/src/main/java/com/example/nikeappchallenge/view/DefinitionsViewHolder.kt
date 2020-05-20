package com.example.nikeappchallenge.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeappchallenge.model.UrbanDictionaryDefinition

class DefinitionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var TAG = "ViewHolder"

    fun onBind(school: UrbanDictionaryDefinition, listener: IClickDefinition) {
        itemView.setOnClickListener { v: View? ->
            listener.onClick(school)
        }
    }

}