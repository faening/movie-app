package com.github.faening.movieapp.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentHomeMovieItemBinding
import com.github.faening.movieapp.databinding.FragmentMovieGenreItemBinding
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.utils.dpToPx

class MovieAdapter(
    private val context: Context,
    private val layoutInflater: Int,
    private val onMovieClickListener: (Int?) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return if (layoutInflater == R.layout.fragment_home_movie_item) {
            val binding = FragmentHomeMovieItemBinding.inflate(LayoutInflater.from(context), parent, false)
            MovieInFragmentHomeViewHolder(binding, onMovieClickListener)
        } else {
            val binding = FragmentMovieGenreItemBinding.inflate(LayoutInflater.from(context), parent, false)
            MovieInFragmentGenreViewHolder(binding, onMovieClickListener)
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, position, itemCount)
    }

    abstract class MovieViewHolder(
        private val binding: ViewBinding,
        private val onMovieClickListener: (Int?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, position: Int, itemCount: Int) {
            loadImage(movie.posterPath)
            setMargin(position, itemCount)
            setClickListener(movie.id)
        }

        abstract fun loadImage(posterPath: String?)
        abstract fun setMargin(position: Int, itemCount: Int)

        fun applyMarginToLastItem(view: View) {
            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.marginEnd = 16.dpToPx(view.context) // Convertendo 16 dp para pixels
            view.layoutParams = layoutParams
        }

        fun resetMargin(view: View) {
            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.marginEnd = 0 // Resetando margem para outros itens
            view.layoutParams = layoutParams
        }

        abstract fun setClickListener(movieId: Int?)
    }

    class MovieInFragmentHomeViewHolder(
        private val binding: FragmentHomeMovieItemBinding,
        private val onMovieClickListener: (Int?) -> Unit
    ) : MovieViewHolder(binding, onMovieClickListener) {
        override fun loadImage(posterPath: String?) {
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500$posterPath")
                .into(binding.movieItemImage)
        }

        override fun setMargin(position: Int, itemCount: Int) {
            if (position == itemCount - 1) applyMarginToLastItem(binding.root)
        }

        override fun setClickListener(movieId: Int?) {
            binding.root.setOnClickListener { onMovieClickListener(movieId) }
        }
    }

    class MovieInFragmentGenreViewHolder(
        private val binding: FragmentMovieGenreItemBinding,
        private val onMovieClickListener: (Int?) -> Unit
    ) : MovieViewHolder(binding, onMovieClickListener) {
        override fun loadImage(posterPath: String?) {
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500$posterPath")
                .into(binding.movieItemImage)
        }

        override fun setMargin(position: Int, itemCount: Int) {
            resetMargin(binding.root)
        }

        override fun setClickListener(movieId: Int?) {
            binding.root.setOnClickListener { onMovieClickListener(movieId) }
        }
    }
}