package com.example.books.data.api

import com.example.books.data.model.Book
import com.example.books.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /*@GET("volumes?")
    suspend fun getBooks(@Query("search") search: String): List<Book>*/

   /* @GET("volumes?")
    suspend fun getBooks(@Query("q") search: String): List<Book>*/

    @GET("volumes?")
    suspend fun getBooks2(@Query("q") search: String): List<Response>
}