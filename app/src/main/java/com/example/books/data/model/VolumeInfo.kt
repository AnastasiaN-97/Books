package com.example.books.data.model

import com.google.gson.annotations.SerializedName


data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    @SerializedName("description") val content: String?,
    @SerializedName("publisher")
    val publisher: String,
    val imageLinks: ImageLinks?
)
