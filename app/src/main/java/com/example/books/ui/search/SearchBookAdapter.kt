package com.example.books.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.data.model.VolumeInfo
import com.example.books.databinding.BookItemForMoreFragmentBinding




class SearchBookAdapter(private val books: MutableList<com.example.books.data.model.Response>) : RecyclerView.Adapter<SearchBookAdapter.DataViewHolder>() {

    var clickListener: UserClickListener? = null

    interface UserClickListener{
        fun onUserClick(userId: String)
    }


    class DataViewHolder(private val binding: BookItemForMoreFragmentBinding,
                         private val listener: UserClickListener?) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: com.example.books.data.model.Response) {
            with(binding) {
                textView.text = book.toString()
                Log.d("mytag2", book.kind)
               // textView2.text = book.author
                /*textView2.text = book.author


                Glide.with(imageView.context)
                    .load(book.cover)
                    .into(imageView)*/
                /*container.setOnClickListener {
                    listener?.onUserClick(user.id)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =  BookItemForMoreFragmentBinding.inflate(
            inflater,
            parent,
            false
        )

        return DataViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(books[position])
    }

    fun addBooks(books: List<com.example.books.data.model.Response>) {
        this.books.apply {
            clear()
            addAll(books)
        }
    }
}