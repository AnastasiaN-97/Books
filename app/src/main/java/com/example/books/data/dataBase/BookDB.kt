package com.example.books.data.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookDB(
    @PrimaryKey()
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "authors")
    val authors: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "imageLinks")
    val imageLinks: String,
    @ColumnInfo(name = "isbn")
    val isbn: String,
    @ColumnInfo(name = "favorites")
    val favorites: Boolean,
)
