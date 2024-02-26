package com.github.faening.movieapp.presentation.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.ReviewItemBinding
import com.github.faening.movieapp.domain.model.MovieReview
import com.github.faening.movieapp.helper.ReadMoreLessHelper
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

    inner class MovieReviewsViewHolder(private val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieReview: MovieReview) {
            loadAndApplyUserImage(
                avatarPath = movieReview.authorDetails?.avatarPath,
                imageView = binding.reviewItemUserImage
            )

            binding.apply {
                reviewItemUserName.text = movieReview.authorDetails?.name

                movieReview.content?.let {
                    ReadMoreLessHelper.addReadMoreText(
                        context = context,
                        description = it,
                        textView = reviewItemDescription,
                        maxLines = context.resources.getInteger(R.integer.review_item_description_max_lines),
                        fadeIn = { view, initialValue -> fadeIn(view, initialValue) },
                        fadeOut = { view, finalValue -> fadeOut(view, finalValue) },
                    )
                }

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
    }

}