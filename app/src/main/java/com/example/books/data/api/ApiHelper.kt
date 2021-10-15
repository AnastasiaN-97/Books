package com.example.books.data.api

class ApiHelper(private val apiService: ApiService) {

    //suspend fun getBooks(search: String) =apiService.getBooks(search)

    suspend fun getBooks2(search: String) =apiService.getBooks2(search)

}