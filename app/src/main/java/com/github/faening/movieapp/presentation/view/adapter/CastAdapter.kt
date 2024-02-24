package com.github.faening.movieapp.presentation.view.adapter

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
import com.github.faening.movieapp.domain.model.MovieCreditsCast

class CastAdapter(
    private val context: Context,
) : ListAdapter<MovieCreditsCast, CastAdapter.CastViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieCreditsCast>() {
            override fun areItemsTheSame(oldItem: MovieCreditsCast, newItem: MovieCreditsCast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieCreditsCast, newItem: MovieCreditsCast): Boolean {
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
        fun bind(movieCreditsCast: MovieCreditsCast) {
            binding.apply {
                loadAndApplyImage(movieCreditsCast.profilePath, castItemImage)
                this.castItemName.text = movieCreditsCast.name
                this.castItemCast.text = movieCreditsCast.knownForDepartment
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