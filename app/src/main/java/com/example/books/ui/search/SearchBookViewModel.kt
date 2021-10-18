package com.example.books.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.books.data.api.ApiHelper
import com.example.books.data.api.RetrofitBuilder
import com.example.books.data.model.Book
import com.example.books.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import com.example.books.utils.Resource

import retrofit2.Response

class SearchBookViewModel : ViewModel() {

    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val mainRepository: MainRepository = MainRepository(apiHelper)

    fun getBooksTest(search: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getBooksTest(search)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}