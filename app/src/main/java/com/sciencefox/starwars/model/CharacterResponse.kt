package com.sciencefox.starwars.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val characterList: List<CharacterResponseModel>
)

data class CharacterResponseModel(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("birth_year")
    var birthYear: String? = null,
    @SerializedName("height")
    var height: String? = null,
    @SerializedName("films")
    var films: ArrayList<String> = arrayListOf()
)

data class FilmsResponse(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("opening_crawl")
    var openingCrawl: String? = null
)

data class Character(
    var name: String? = null,
    var birthYear: String? = null,
    var height: String? = null,
    var films: ArrayList<String>? = arrayListOf(),
    var crawl: ArrayList<String>? = arrayListOf()
)
