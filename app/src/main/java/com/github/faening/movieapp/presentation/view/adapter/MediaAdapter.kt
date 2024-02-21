package com.github.faening.movieapp.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.MediaItemBinding
import com.github.faening.movieapp.domain.model.MediaItem
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.utils.ItemLayoutType
import com.github.faening.movieapp.utils.dpToPx

class MediaAdapter(
    private val context: Context,
    private val itemLayoutType: ItemLayoutType,
    private val onMediaItemClick: (Int?) -> Unit
) : ListAdapter<MediaItem, MediaAdapter.MediaViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = MediaItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MediaViewHolder(binding, onMediaItemClick)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val mediaItem = getItem(position)
        holder.bind(mediaItem)

        // Set the layout params for the view
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = R.dimen.card_height.dpToPx(context)

        when (itemLayoutType) {
            ItemLayoutType.SMALL -> {
                layoutParams.width = R.dimen.card_width_small.dpToPx(context)
            }

            ItemLayoutType.LARGE -> {
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            }
        }
        holder.itemView.layoutParams = layoutParams
    }

    class MediaViewHolder(
        private val binding: ViewBinding,
        private val onMovieClickListener: (Int?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mediaItem: MediaItem) {
            when (mediaItem) {
                is Movie -> {

                    // TODO: Implement this method
                }

                else -> {
                    // TODO: Implement this method
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MediaItem>() {
            override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}