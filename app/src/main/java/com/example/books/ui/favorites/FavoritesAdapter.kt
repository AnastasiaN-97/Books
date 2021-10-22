package com.example.books.ui.favorites

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.data.dataBase.BookDB
import com.example.books.databinding.BookItemBinding

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder>() {

    private var bookList = emptyList<BookDB>()

    var clickListener: BookClickListener? = null

    interface BookClickListener{
        fun onBookClick(bokId: String?, bookTitle: String, bookAuthor: String,
                        bookImage: String, bookContent: String, bookInfo: String)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(
            inflater,
            parent,
            false
        )

        return FavoritesHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.bind(bookList[position])
    }
    fun setData(book: List<BookDB>){
        this.bookList = book
        notifyDataSetChanged()
    }

    class FavoritesHolder(
        private val binding: BookItemBinding,
        private val listener: BookClickListener?
    )
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookDB) {
            with(binding) {
                textView2.text = book.authors
                textView.text  = book.title

                Log.d("ima", book.imageLinks)
                Glide.with(imageView.context)
                    .load(book.imageLinks)
                    .into(imageView)

                bookCard.setOnClickListener {
                    listener?.onBookClick(book.isbn, bookAuthor = book.authors, bookTitle = book.title, bookImage = book.imageLinks, bookContent = book.content, bookInfo = "jhgvv")
                }
            }
        }
    }
}