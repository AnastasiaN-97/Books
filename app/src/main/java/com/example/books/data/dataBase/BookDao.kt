package com.example.books.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books: BookDB)

    @Query("SELECT * FROM books")
    fun readAllData(): LiveData<List<BookDB>>

    @Delete
    suspend fun deleteBook(books: BookDB)

    @Query("SELECT EXISTS (SELECT * FROM books WHERE isbn = :id AND title = :bookTitle)")
    fun isFavorites(id: String, bookTitle:String): LiveData<Boolean>
}