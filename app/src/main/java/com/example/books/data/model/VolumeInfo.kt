package com.example.books.data.model

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    @SerializedName("description")
    val content: String?,
    val publisher: String,
    val imageLinks: ImageLinks?,
    @SerializedName("industryIdentifiers")
    val isbn: List<Type>?,
    val averageRating: Double?,
    val infoLink: String
)
