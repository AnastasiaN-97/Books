package com.example.books.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.BookItemBinding
import com.example.books.fake.Book

class FavoritesAdapter(var books: List<Book>): RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder>() {

    var clickListener: TicketClickListener? = null

    interface TicketClickListener{
        fun onTicketClick()
    }

    override fun getItemCount(): Int {
        return books.size
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
        holder.bind(books[position])
    }

    class FavoritesHolder(
        private val binding: BookItemBinding,
        private val listener: TicketClickListener?
    )
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            with(binding) {
                textView2.text = book.author
                textView.text  = book.title

                bookCard.setOnClickListener {
                    listener?.onTicketClick()
                }
            }
        }
    }
}