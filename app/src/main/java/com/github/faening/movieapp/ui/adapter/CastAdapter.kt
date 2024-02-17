package com.github.faening.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.CastItemBinding
import com.github.faening.movieapp.domain.model.Cast

class CastAdapter(
    private val context: Context,
) : ListAdapter<Cast, CastAdapter.CastViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Cast>() {
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = CastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = getItem(position)
        holder.bind(cast)
    }

    inner class CastViewHolder(val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.apply {
                loadAndApplyImage(cast.profilePath, castItemImage)
                this.castItemName.text = cast.name
                this.castItemCast.text = cast.knownForDepartment
            }
        }
    }

    private fun loadAndApplyImage(imagePath: String?, imageView: ImageView) {
        if (imagePath == null) {
            imageView.setImageResource(R.drawable.img_person_profile)
        } else {
            Glide.with(context).load("https://image.tmdb.org/t/p/w200$imagePath").into(imageView)
        }
    }
}