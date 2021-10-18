package com.example.books.ui.moreBooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.databinding.MoreBooksFragmentBinding
import com.example.books.fake.bookData

class MoreBooksFragment : Fragment(R.layout.more_books_fragment)  {

    private lateinit var binding: MoreBooksFragmentBinding

    /*override fun onBookClick() {
        val action = MoreBooksFragmentDirections.actionMoreBooksFragmentToBookInfoFragment(bookId = "3")
        findNavController().navigate(action)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoreBooksFragmentBinding.inflate(
            inflater,
            container,
            false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoreBooksAdapter(bookData)
        //adapter.clickListener = this
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false)
    }
}