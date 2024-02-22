package com.github.faening.movieapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.faening.movieapp.databinding.HomeMoviesItemBinding
import com.github.faening.movieapp.presentation.model.GenrePresentation
import com.github.faening.movieapp.utils.ItemLayoutType

class GenreWithMoviesAdapter(
    private val onMovieClickListener: (Int?) -> Unit, private var buttonShowAllListener: (Int, String) -> Unit
) : ListAdapter<GenrePresentation, GenreWithMoviesAdapter.GenreWithMoviesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreWithMoviesViewHolder {
        val binding = HomeMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreWithMoviesViewHolder(binding, onMovieClickListener, buttonShowAllListener)
    }

    override fun onBindViewHolder(holder: GenreWithMoviesViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre)
    }

    inner class GenreWithMoviesViewHolder(
        private val binding: HomeMoviesItemBinding,
        private val onMovieClickListener: (Int?) -> Unit,
        private val buttonShowAllListener: (Int, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val mediaAdapter by lazy {
            MediaAdapter(context = binding.root.context, itemLayoutType = ItemLayoutType.SMALL, onMediaItemClick = onMovieClickListener)
        }

        init {
            with(binding.genreItemRecyclerView) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = mediaAdapter
            }
        }

        fun bind(genre: GenrePresentation) {
            with(binding) {
                genreItemName.text = genre.name
                genreItemShowAll.setOnClickListener { genre.id?.let { buttonShowAllListener(it, genre.name ?: "") } }
            }

            mediaAdapter.submitList(genre.movies)
        }
    }

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

}