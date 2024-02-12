package com.github.faening.movieapp.ui.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.faening.movieapp.domain.model.Movie

class MovieAdapter(
    private val context: Context,
    private val layoutInflater: Int,
    private val onMovieClickListener: (Int?) -> Unit
) : ListAdapter<Movie, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(com.github.faening.movieapp.R.id.movie_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        Glide
            .with(context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.movieImage)

        // Aplicar margem para o último item da lista
        if (layoutInflater == com.github.faening.movieapp.R.layout.movie_item_home && position == itemCount - 1) {
            applyMarginToLastItem(holder.itemView)
        } else {
            resetMargin(holder.itemView)
        }

        // Clique no item
        holder.itemView.setOnClickListener { onMovieClickListener(movie.id) }
    }

    private fun applyMarginToLastItem(view: View) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.marginEnd = 16.dpToPx(view.context) // Convertendo 16 dp para pixels
        view.layoutParams = layoutParams
    }

    private fun resetMargin(view: View) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.marginEnd = 0 // Resetando margem para outros itens
        view.layoutParams = layoutParams
    }
}

fun Int.dpToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}