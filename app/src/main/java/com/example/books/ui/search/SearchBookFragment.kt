package com.example.books.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.SearchBookFragmentBinding
import com.example.books.utils.Status
import com.google.android.material.snackbar.Snackbar

class SearchBookFragment : Fragment(com.example.books.R.layout.search_book_fragment), SearchBookAdapter.BookClickListener {

    private lateinit var viewModel: SearchBookViewModel
    private lateinit var adapter: SearchBookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: SearchBookFragmentBinding


    override fun onBookClick(bokId: String?, bookTitle: String, bookAuthor: String,
                             bookImage: String, bookContent: String, bookInfo: String) {
        val action = SearchBookFragmentDirections.
        actionSearchBookFragmentToBookInfoFragment(bokId!!, bookTitle, bookAuthor, bookImage,bookContent, bookInfo)
        findNavController().navigate(action)
    }

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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String?): Boolean {
                if(search != null && search.isNotEmpty())
                {
                    setupUI()
                    setupObservers(search)
                }

                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null && newText.isNotEmpty())
                {
                    setupUI()
                    setupObservers(newText)
                }
                return false
            }
        })
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
                    resource.data!!.items.let{ test -> adapter.addBooks(test)}
                    adapter.clickListener = this
                }
                Status.ERROR -> {
                    recyclerView.visibility = View.GONE
                    //Snackbar.make(this,"NoInternet", Snackbar.LENGTH_LONG)
                    Toast.makeText(context, "Проверьте соединение с сетью", Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    recyclerView.visibility = View.GONE
                }
            }
        }
    }
}