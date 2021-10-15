package com.example.books.ui.moreBooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.BookItemForMoreFragmentBinding
import com.example.books.fake.Book

class MoreBooksAdapter (var books: List<Book>): RecyclerView.Adapter<MoreBooksAdapter.MoreBooksHolder>() {

        var clickListener: TicketClickListener? = null

        interface TicketClickListener{
            fun onTicketClick()
        }

        override fun getItemCount(): Int {
            return books.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreBooksAdapter.MoreBooksHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = BookItemForMoreFragmentBinding.inflate(
                inflater,
                parent,
                false
            )

            return MoreBooksHolder(binding, clickListener)
        }

        override fun onBindViewHolder(holder: MoreBooksHolder, position: Int) {
            holder.bind(books[position])
        }

        class MoreBooksHolder(
            private val binding: BookItemForMoreFragmentBinding,
            private val listener: TicketClickListener?
        )
            : RecyclerView.ViewHolder(binding.root) {
            fun bind(books: Book) {
                with(binding) {
                    textView2.text = books.author
                    textView.text  = books.title

                    moreBooks.setOnClickListener {
                        listener?.onTicketClick()
                    }
                }
            }
        }
    }