package com.github.faening.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieReviewAuthorDetails(
    val name: String?,
    val username: String?,
    val avatarPath: String?,
    val rating: Float?
) : Parcelable
