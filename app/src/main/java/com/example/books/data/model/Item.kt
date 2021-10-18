package com.example.books.data.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo,
    @SerializedName("categories")
    val genre: Genre?,
)