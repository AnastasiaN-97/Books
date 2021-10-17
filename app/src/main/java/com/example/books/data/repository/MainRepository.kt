package com.example.books.data.repository

import com.example.books.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    //suspend fun getBooks(search: String) = apiHelper.getBooks(search)
    //suspend fun getBooks2(search: String) = apiHelper.getBooks2(search)

    //suspend fun getBooks2(search: String) = apiHelper.getBooks2(search)

    suspend fun getBooksTest(search: String) = apiHelper.getBooksTest(search)

}