package com.example.books.data.model

import com.google.gson.annotations.SerializedName

data class Book(

    @SerializedName("title")
    val title: String,

    @SerializedName("authors")
    val author: String,

    @SerializedName("imageLinks")
    val cover: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("categories")
    val genre: String
)
