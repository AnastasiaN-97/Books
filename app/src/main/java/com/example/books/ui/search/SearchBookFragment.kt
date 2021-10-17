package com.example.books.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.data.api.ApiService
import com.example.books.data.model.Book
import com.example.books.data.model.Item
import com.example.books.databinding.SearchBookFragmentBinding
import com.example.books.utils.Resource
import com.example.books.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchBookFragment : Fragment(R.layout.search_book_fragment) {

    private lateinit var viewModel: SearchBookViewModel
    private lateinit var adapter: SearchBookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: SearchBookFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchBookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchBookViewModel::class.java)
        recyclerView = binding.moreRecyclerView

        binding.searchBtn.setOnClickListener{
            val search = binding.searchEdit.text.toString()
            setupUI()
            setupObservers(search)
        }

    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SearchBookAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers(search: String) {
        viewModel.getBooksTest(search).observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    recyclerView.visibility = View.VISIBLE
                    resource.data!!.items.let{ test -> adapter.addBooks(test) }
                }
                Status.ERROR -> {
                    recyclerView.visibility = View.VISIBLE
                    Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    recyclerView.visibility = View.GONE
                }
            }
        }
    }
}