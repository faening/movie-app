package com.github.faening.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieReview(
    val author: String?,
    val authorDetails: MovieReviewAuthorDetails?,
    val content: String?,
    val createdAt: String?,
    val id: String?,
    val updatedAt: String?,
    val url: String?
) : Parcelable
