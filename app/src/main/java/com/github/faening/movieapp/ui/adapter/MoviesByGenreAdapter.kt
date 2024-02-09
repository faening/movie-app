package com.github.faening.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.faening.movieapp.databinding.GenreItemBinding
import com.github.faening.movieapp.ui.model.GenrePresentation

class MoviesByGenreAdapter(
    var buttonShowAllListener: (Int, String) -> Unit
) : ListAdapter<GenrePresentation, MoviesByGenreAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GenrePresentation>() {
            override fun areItemsTheSame(oldItem: GenrePresentation, newItem: GenrePresentation): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GenrePresentation, newItem: GenrePresentation): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = getItem(position)
        val movieAdapter = MovieAdapter(
            context = holder.binding.root.context,
            layoutInflater = com.github.faening.movieapp.R.layout.movie_item_home
        )
        val layoutManager = LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)

        with(holder.binding) {
            genreItemName.text = genre.name
            genreItemShowAll.setOnClickListener { genre.id?.let { buttonShowAllListener(genre.id, genre.name ?: "") } }
            genreItemRecyclerView.layoutManager = layoutManager
            genreItemRecyclerView.setHasFixedSize(true)
            genreItemRecyclerView.adapter = movieAdapter
        }

        movieAdapter.submitList(genre.movies)
    }
}