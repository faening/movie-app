package com.github.faening.movieapp.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.MediaItemBinding
import com.github.faening.movieapp.domain.model.MediaItem
import com.github.faening.movieapp.utils.ItemLayoutType

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
        setViewLayoutParams(holder, position, itemLayoutType)
    }

    private fun setViewLayoutParams(holder: MediaViewHolder, position: Int, layoutType: ItemLayoutType) {
        val itemView = holder.itemView
        val layoutParams = itemView.layoutParams
        val smallCardWidth = getDimension(itemView, R.dimen.card_width_small)
        val mediumMargin = getDimension(itemView, R.dimen.margin_medium)
        val smallMargin = getDimension(itemView, R.dimen.margin_small)

        when (layoutType) {
            ItemLayoutType.SMALL -> setSmallLayoutParams(itemView, layoutParams, position, smallCardWidth, mediumMargin)
            ItemLayoutType.LARGE -> setLargeLayoutParams(itemView, layoutParams, position, mediumMargin, smallMargin)
        }
    }

    private fun setSmallLayoutParams(
        itemView: View,
        layoutParams: ViewGroup.LayoutParams,
        position: Int,
        smallCardWidth: Int,
        mediumMargin: Int
    ) {
        layoutParams.width = smallCardWidth
        val marginLayoutParams = ViewGroup.MarginLayoutParams(layoutParams).apply {
            rightMargin = mediumMargin
            if (position == 0) leftMargin = mediumMargin
        }
        itemView.layoutParams = marginLayoutParams
    }

    private fun setLargeLayoutParams(
        itemView: View,
        layoutParams: ViewGroup.LayoutParams,
        position: Int,
        mediumMargin: Int,
        smallMargin: Int
    ) {
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        val marginLayoutParams = ViewGroup.MarginLayoutParams(layoutParams).apply {
            bottomMargin = mediumMargin
            if (position % 2 == 0) {
                rightMargin = smallMargin
            } else {
                leftMargin = smallMargin
            }
        }
        itemView.layoutParams = marginLayoutParams
    }

    private fun getDimension(view: View, resId: Int): Int {
        return view.context.resources.getDimension(resId).toInt()
    }

    class MediaViewHolder(
        private val binding: MediaItemBinding,
        private val onMovieClickListener: (Int?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mediaItem: MediaItem) {
            applyMediaImage(mediaItem.posterPath)
            setClickListener(mediaItem.id)
        }

        private fun applyMediaImage(posterPath: String?) {
            binding.apply {
                Glide
                    .with(root.context)
                    .load("https://image.tmdb.org/t/p/w500$posterPath")
                    .into(genericCardItemImage)
            }
        }

        private fun setClickListener(mediaItemId: Int?) {
            binding.root.setOnClickListener {
                onMovieClickListener(mediaItemId)

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