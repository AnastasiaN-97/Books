package com.example.books.data.model


data class Response(
    var items: List<Item>,
    var kind: String,
    var totalItems: Int
)
