package com.example.books.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.GenreItemBinding
import com.example.books.fake.Book
import com.example.books.fake.Genre

class HomeAdapter(var genres: List<Genre>): RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    var clickListener: TicketClickListener? = null

    interface TicketClickListener{
        fun onTicketClick()
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GenreItemBinding.inflate(
            inflater,
            parent,
            false
        )

        return HomeHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(genres[position])
    }

    class HomeHolder(
        private val binding: GenreItemBinding,
        private val listener: TicketClickListener?
    )
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            with(binding) {
                genreText.text = genre.genre
                genreCard.setOnClickListener {
                    listener?.onTicketClick()
                }
            }
        }
    }
}