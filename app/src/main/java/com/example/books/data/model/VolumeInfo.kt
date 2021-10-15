package com.example.books.data.model

data class VolumeInfo(
    var authors: List<String> = listOf(),
    var categories: List<String> = listOf(),
    var description: String = "",
    var imageLinks: ImageLinks = ImageLinks(),
    var language: String = "",
    var publishedDate: String = "",
    var publisher: String = "",
    var title: String = ""
)
