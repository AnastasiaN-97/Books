package com.example.books.data.repository

import androidx.lifecycle.LiveData
import com.example.books.data.dataBase.BookDB
import com.example.books.data.dataBase.BookDao

class BookRepository(private val bookDao: BookDao) {

    val readAllData: LiveData<List<BookDB>> = bookDao.readAllData()

    suspend fun insert(book: BookDB){
        bookDao.insert(book)
    }

    suspend fun deleteBook(book: BookDB){
        bookDao.deleteBook(book)
    }

    fun isFavorite(id: String, bookTitle: String){
        bookDao.isFavorites(id, bookTitle)
    }
}