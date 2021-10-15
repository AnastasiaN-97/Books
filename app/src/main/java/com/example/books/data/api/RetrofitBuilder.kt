package com.example.books.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
   val interceptor = HttpLoggingInterceptor().apply {
       level = HttpLoggingInterceptor.Level.BODY
   }

    val client  = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}