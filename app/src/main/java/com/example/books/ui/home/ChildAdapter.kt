package com.example.books.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.BookItemBinding
import com.example.books.databinding.GenreItemBinding
import com.example.books.fake.Book
import com.example.books.fake.Genre

class ChildAdapter (var books: List<Book>): RecyclerView.Adapter<ChildAdapter.ChildHolder>() {

    var clickListener: TicketClickListener? = null

    interface TicketClickListener{
        fun onTicketClick()
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(
            inflater,
            parent,
            false
        )

        return ChildAdapter.ChildHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        holder.bind(books[position])
    }

    class ChildHolder(
        private val binding: BookItemBinding,
        private val listener: TicketClickListener?
    )
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(books: Book) {
            with(binding) {
                textView2.text = books.author
                textView.text  = books.title

                bookCard.setOnClickListener {
                    listener?.onTicketClick()
                }
            }
        }
    }
}