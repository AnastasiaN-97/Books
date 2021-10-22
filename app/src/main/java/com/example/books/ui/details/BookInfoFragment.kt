package com.example.books.ui.details

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.data.dataBase.BookDB
import com.example.books.databinding.BookInfoFragmentBinding
import com.example.books.ui.search.SearchBookViewModel

class BookInfoFragment : Fragment(R.layout.book_info_fragment) {

    private lateinit var bookViewModel: SearchBookViewModel
    private lateinit var binding: BookInfoFragmentBinding
    private lateinit var viewModel: SearchBookViewModel

    private val args: BookInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookViewModel = ViewModelProvider(this).get(SearchBookViewModel::class.java)


        binding = BookInfoFragmentBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchBookViewModel::class.java)


        binding.favoriteBtn.setOnClickListener {
            val book = BookDB(
                args.bookTitle,
                args.bookAuthor,
                args.bookContent,
                args.bookImage,
                args.bookid,
                true
            )

            bookViewModel.insert(book)
            Toast.makeText(requireContext(), "Книга добавлена в избранное!", Toast.LENGTH_LONG).show()

            /*bookViewModel.isFavorite(args.bookid, args.bookTitle).observe(viewLifecycleOwner) {
                binding.favoriteBtn.setImageResource(R.drawable.ic_red_favorite)
            }*/
        }

        binding.shareBtn.setOnClickListener {
            shareInfo(args.bookTitle, args.bookAuthor)
        }

        showInfo(args.bookTitle, args.bookAuthor, args.bookImage, args.bookContent)
    }


    private fun shareInfo(bookTitle: String,bookAuthor: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "$bookTitle\n$bookAuthor ")
        startActivity(Intent.createChooser(intent, "Share"))

    }

    private fun showInfo(bookTitle:String, bookAuthor: String, bookImage: String, bookContent: String){
        binding.titleBook.text = bookTitle
        binding.authorBook.text = bookAuthor
        binding.descriptionBook.text = bookContent
        Glide.with(binding.bookImg.context)
            .load(bookImage)
            .into(binding.bookImg)
    }
}