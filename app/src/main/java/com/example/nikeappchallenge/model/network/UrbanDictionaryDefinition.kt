package com.example.nikeappchallenge.model.network

class UrbanDictionaryDefinition(
    var definition: String,
    var example: String,
    var thumbs_up: Int,
    var thumbs_down: Int,
    var author: String
)
data class DescriptionList(
    var list: List<UrbanDictionaryDefinition>)