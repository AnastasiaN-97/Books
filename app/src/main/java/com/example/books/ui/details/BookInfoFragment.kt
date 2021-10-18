package com.example.books.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.data.model.Book
import com.example.books.databinding.BookInfoFragmentBinding
import com.example.books.ui.search.SearchBookViewModel
import com.example.books.utils.Status

class BookInfoFragment : Fragment(R.layout.book_info_fragment) {

    private lateinit var binding: BookInfoFragmentBinding
    private lateinit var viewModel: SearchBookViewModel
    private lateinit var progressBar: ProgressBar

    private val args: BookInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchBookViewModel::class.java)
        progressBar = binding.progressBar

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getBooksTest(args.bookId).observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    binding.shareBtn.visibility = View.VISIBLE
                    binding.favoriteBtn.visibility = View.VISIBLE

                    resource.data?.let {
                        showInfo(it)
                    }
                }
                Status.ERROR -> { Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show() }

                Status.LOADING -> {progressBar.visibility = View.VISIBLE}
            }
        }
    }

    fun showInfo(book: Book){
        binding.titleBook.text = book.items[0].volumeInfo.title
        val size = if (book.items[0].volumeInfo.authors != null) book.items[0].volumeInfo.authors!!.size else -1
        var k = 0

        while (size>k){
            binding.authorBook.text= book.items[0].volumeInfo.authors!![0] + " "
            k++
        }

        val content = if (book.items[0].volumeInfo.content != null) book.items[0].volumeInfo.content else "Упс, описания для книги не представлено"
        binding.descriptionBook.text = content
        val img = if (book.items[0].volumeInfo.imageLinks?.thumbnail != null) book.items[0].volumeInfo.imageLinks!!.thumbnail!!.replace("http", "https") else "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Keen_Bild_Taxon.svg/1200px-Keen_Bild_Taxon.svg.png"
        Glide.with(binding.bookImg.context)
            .load(img)
            .into(binding.bookImg)
    }
}