package com.example.books.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.data.model.Item
import com.example.books.databinding.BookItemForMoreFragmentBinding

class SearchBookAdapter(private val responses: MutableList<Item>) : RecyclerView.Adapter<SearchBookAdapter.DataViewHolder>() {

    var clickListener: BookClickListener? = null

    interface BookClickListener{
        fun onBookClick(bokId: String?, bookTitle: String, bookAuthor: String, bookImage: String, bookContent: String, bookInfo: String)
    }

    class DataViewHolder(private val binding: BookItemForMoreFragmentBinding,
                         private val listener: BookClickListener?) : RecyclerView.ViewHolder(binding.root) {

        fun bind(response: Item) {
            with(binding) {
                val title = response.volumeInfo.title ?: ""
                textView.text = title

                val author = if (response.volumeInfo.authors != null)  response.volumeInfo.authors[0] else ""
                textView2.text = author

                val rating = response.volumeInfo.averageRating ?: 0.0
                ratingBook.rating = rating.toFloat()

                val img = if (response.volumeInfo.imageLinks?.thumbnail != null) response.volumeInfo.imageLinks.thumbnail.replace("http", "https") else "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Keen_Bild_Taxon.svg/1200px-Keen_Bild_Taxon.svg.png"
                Glide.with(imageView.context)
                    .load(img)
                    .into(imageView)

                val content = response.volumeInfo.content ?: "Описание не представлено"
                val bookId = if (response.volumeInfo.isbn != null && response.volumeInfo.isbn[0].identifier != null)  response.volumeInfo.isbn[0].identifier else " "
                
                moreBooks.setOnClickListener{
                    listener?.onBookClick(bookId, title, author, img, content, response.volumeInfo.infoLink)
                }
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