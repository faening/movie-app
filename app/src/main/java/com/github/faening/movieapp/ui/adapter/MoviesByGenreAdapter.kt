package com.github.faening.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.faening.movieapp.databinding.GenreItemBinding
import com.github.faening.movieapp.ui.model.GenrePresentation

class MoviesByGenreAdapter : ListAdapter<GenrePresentation, MoviesByGenreAdapter.ViewHolder>(DIFF_CALLBACK) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = getItem(position)
        val movieAdapter = MovieAdapter(holder.binding.root.context)
        val layoutManager = LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)

        holder.binding.genreItemName.text = genre.name
        holder.binding.genreItemRecyclerView.layoutManager = layoutManager
        holder.binding.genreItemRecyclerView.setHasFixedSize(true)
        holder.binding.genreItemRecyclerView.adapter = movieAdapter
        movieAdapter.submitList(genre.movies)
    }

    inner class ViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)
}