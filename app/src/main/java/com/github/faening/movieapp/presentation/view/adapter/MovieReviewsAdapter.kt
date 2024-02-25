package com.github.faening.movieapp.presentation.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.ReviewItemBinding
import com.github.faening.movieapp.domain.model.MovieReview
import com.github.faening.movieapp.utils.fadeIn
import com.github.faening.movieapp.utils.fadeOut
import com.github.faening.movieapp.utils.formatDateToRelativeTime

class MovieReviewsAdapter(
    private val context: Context
) : RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewsViewHolder>() {

    private val movieReviews: MutableList<MovieReview> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewsViewHolder {
        val binding = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieReviewsViewHolder, position: Int) {
        val movieReview = movieReviews[position]
        holder.bind(movieReview)
    }

    override fun getItemCount(): Int = movieReviews.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(reviews: List<MovieReview>) {
        movieReviews.clear()
        movieReviews.addAll(reviews)
        notifyDataSetChanged()
    }

    inner class MovieReviewsViewHolder(
        private val binding: ReviewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieReview: MovieReview) {
            loadAndApplyUserImage(
                avatarPath = movieReview.authorDetails?.avatarPath,
                imageView = binding.reviewItemUserImage
            )

            binding.apply {
                reviewItemUserName.text = movieReview.authorDetails?.name

                val description = movieReview.content
                reviewItemDescription.maxLines = context.resources.getInteger(R.integer.review_item_description_max_lines)
                val spannableDescription = description?.let { addReadMoreText(it) }
                reviewItemDescription.text = spannableDescription
                reviewItemDescription.movementMethod = LinkMovementMethod.getInstance()

                reviewItemRatingValue.text = movieReview.authorDetails?.rating.toString()
                reviewItemDate.text = formatDateToRelativeTime(movieReview.createdAt.toString())
            }
        }

        private fun loadAndApplyUserImage(avatarPath: String?, imageView: ImageView) {
            binding.apply {
                if (avatarPath == null) {
                    imageView.setImageResource(R.drawable.img_person_profile)
                } else {
                    Glide.with(context).load("https://image.tmdb.org/t/p/w500$avatarPath").into(imageView)
                }
            }
        }

        private fun addReadMoreText(description: String): SpannableStringBuilder {
            val readMoreText = " ...Ler Mais"
            val spannableDescription = SpannableStringBuilder(description).append(readMoreText)
            val clickableSpan: ClickableSpan = object : ClickableSpan() { override fun onClick(widget: View) { setupDescriptionToggle() } }
            spannableDescription.setSpan(clickableSpan, description.length, spannableDescription.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spannableDescription
        }

        private fun setupDescriptionToggle() {
            binding.reviewItemDescription.setOnClickListener {
                if (binding.reviewItemDescription.maxLines == Integer.MAX_VALUE) {
                    fadeOut(view = binding.reviewItemDescription, finalValue = 0.5f)
                    binding.reviewItemDescription.maxLines = context.resources.getInteger(R.integer.review_item_description_max_lines)
                    fadeIn(view = binding.reviewItemDescription, initialValue = 0.5f)
                } else {
                    fadeOut(view = binding.reviewItemDescription, finalValue = 0.5f)
                    binding.reviewItemDescription.maxLines = Integer.MAX_VALUE
                    fadeIn(view = binding.reviewItemDescription, initialValue = 0.5f)
                }
            }
        }
    }

}