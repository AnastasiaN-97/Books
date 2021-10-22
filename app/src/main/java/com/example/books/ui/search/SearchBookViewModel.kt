package com.example.books.ui.search

import android.app.Application
import androidx.lifecycle.*
import com.example.books.data.repository.BookRepository
import com.example.books.data.api.ApiHelper
import com.example.books.data.api.RetrofitBuilder
import com.example.books.data.dataBase.AppDatabase
import com.example.books.data.dataBase.BookDB
import com.example.books.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import com.example.books.utils.Resource
import kotlinx.coroutines.launch

class SearchBookViewModel(application: Application): AndroidViewModel(application){

    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val mainRepository: MainRepository = MainRepository(apiHelper)
    private val repository: BookRepository
    val readAllData: LiveData<List<BookDB>>

    init {
        val bookDao = AppDatabase.getDatabase(
            application
        ).bookDao()
        repository = BookRepository(bookDao)
        readAllData = repository.readAllData
    }

    fun getBooksTest(search: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getBooksTest(search)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insert(book: BookDB){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(book)
        }
    }

    fun deleteBook(book: BookDB){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(book)
        }
    }

    fun isFavorite(id: String, bookTitle: String) = liveData<Boolean> {
        repository.isFavorite(id, bookTitle)
    }
}