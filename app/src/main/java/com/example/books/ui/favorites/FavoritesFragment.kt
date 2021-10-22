package com.example.books.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.databinding.FavoritesFragmentBinding
import com.example.books.ui.search.SearchBookFragmentDirections
import com.example.books.ui.search.SearchBookViewModel

class FavoritesFragment : Fragment(R.layout.favorites_fragment), FavoritesAdapter.BookClickListener  {

    private lateinit var binding: FavoritesFragmentBinding
    private lateinit var bookViewModel: SearchBookViewModel


    override fun onBookClick(bokId: String?, bookTitle: String, bookAuthor: String,
                             bookImage: String, bookContent: String, bookInfo: String) {
        val action = FavoritesFragmentDirections.
        actionFavoritesFragmentToBookInfoFragment(bokId!!, bookTitle, bookAuthor, bookImage,bookContent, bookInfo)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritesFragmentBinding.inflate(
            inflater,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavoritesAdapter()
        adapter.clickListener = this
        binding.res.adapter = adapter
        binding.res.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        bookViewModel = ViewModelProvider(this).get(SearchBookViewModel::class.java)
        bookViewModel.readAllData.observe(viewLifecycleOwner, Observer { book ->
            adapter.setData(book)
        })
    }
}