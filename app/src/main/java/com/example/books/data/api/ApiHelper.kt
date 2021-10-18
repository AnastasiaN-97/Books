package com.example.books.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getBooksTest(search: String) =apiService.getBooksTest(search)
}