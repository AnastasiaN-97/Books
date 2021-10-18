package com.example.books.data.api

import com.example.books.data.model.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes?")
    suspend fun getBooksTest(@Query("q") search: String): Book
}