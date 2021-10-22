package com.example.books.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookDB::class], version = 1)

abstract class AppDatabase: RoomDatabase() {

    abstract fun bookDao() : BookDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "book"

        fun getDatabase(context: Context): AppDatabase {
            if(INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
                }
            }

            return INSTANCE!!
        }

        //fun getDatabase() = INSTANCE!!
    }
}
