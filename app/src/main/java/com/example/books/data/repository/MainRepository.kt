package com.example.books.data.repository

import com.example.books.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getBooksTest(search: String) = apiHelper.getBooksTest(search)
}