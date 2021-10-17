package com.example.books.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.data.model.Book
import com.example.books.data.model.Item
import com.example.books.databinding.BookItemForMoreFragmentBinding




class SearchBookAdapter(private val responses: MutableList<Item>) : RecyclerView.Adapter<SearchBookAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: BookItemForMoreFragmentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(response: Item) {
            with(binding) {
                textView.text = response.volumeInfo.title
                val size = if (response.volumeInfo.authors != null) response.volumeInfo.authors.size else -1
                var k = 0

                while (size>k){
                    textView2.text = response.volumeInfo.authors!![0] + " "
                    k++
                }

                val img = if (response.volumeInfo.imageLinks?.thumbnail != null) response.volumeInfo.imageLinks.thumbnail.replace("http", "https") else "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Keen_Bild_Taxon.svg/1200px-Keen_Bild_Taxon.svg.png"
                Glide.with(imageView.context)
                    .load(img)
                    .into(imageView)
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

        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = responses.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(responses[position])
    }

    fun addBooks(books: List<Item>) {
        this.responses.apply {
            clear()
            addAll(books)
        }
    }
}