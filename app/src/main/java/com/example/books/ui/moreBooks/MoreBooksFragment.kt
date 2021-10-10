package com.example.books.ui.moreBooks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.databinding.HomeFragmentBinding
import com.example.books.databinding.MoreBooksFragmentBinding
import com.example.books.fake.bookData
import com.example.books.fake.genre
import com.example.books.ui.home.HomeAdapter
import com.example.books.ui.home.HomeFragmentDirections

class MoreBooksFragment : Fragment(R.layout.more_books_fragment),  MoreBooksAdapter.TicketClickListener  {

    private lateinit var binding: MoreBooksFragmentBinding

    override fun onTicketClick() {
        val action = MoreBooksFragmentDirections.actionMoreBooksFragmentToBookInfoFragment()
        findNavController().navigate(action)
    }

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
        adapter.clickListener = this
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false)

        /*binding.btnMap.setOnClickListener {
            val action = HomeFragmentDirections.toDetails(2)
            findNavController().navigate(action)
        }*/

    }

}