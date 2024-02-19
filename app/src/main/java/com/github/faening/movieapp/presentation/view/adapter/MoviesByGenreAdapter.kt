package com.github.faening.movieapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentHomeMoviesByGenreItemBinding
import com.github.faening.movieapp.presentation.model.GenrePresentation

class MoviesByGenreAdapter(
    private var buttonShowAllListener: (Int, String) -> Unit,
    private val onMovieClickListener: (Int?) -> Unit
) : ListAdapter<GenrePresentation, MoviesByGenreAdapter.MoviesByGenreViewHolder>(DIFF_CALLBACK) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByGenreViewHolder {
        val binding = FragmentHomeMoviesByGenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesByGenreViewHolder(binding, onMovieClickListener, buttonShowAllListener)
    }

    override fun onBindViewHolder(holder: MoviesByGenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre)
    }

    inner class MoviesByGenreViewHolder(
        private val binding: FragmentHomeMoviesByGenreItemBinding,
        private val onMovieClickListener: (Int?) -> Unit,
        private val buttonShowAllListener: (Int, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val movieAdapter = MovieAdapter(
            context = binding.root.context, layoutInflater = R.layout.fragment_home_movie_item, onMovieClickListener = onMovieClickListener
        )

        init {
            with(binding.genreItemRecyclerView) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

        fun bind(genre: GenrePresentation) {
            with(binding) {
                genreItemName.text = genre.name
                genreItemShowAll.setOnClickListener { genre.id?.let { buttonShowAllListener(it, genre.name ?: "") } }
            }

            movieAdapter.submitList(genre.movies)
        }
    }
}