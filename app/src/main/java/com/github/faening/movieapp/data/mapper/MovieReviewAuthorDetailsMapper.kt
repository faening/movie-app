package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.MovieReviewAuthorDetailsResponse
import com.github.faening.movieapp.domain.model.MovieReviewAuthorDetails

fun MovieReviewAuthorDetailsResponse.toDomain(): MovieReviewAuthorDetails {
    return MovieReviewAuthorDetails(
        name = name,
        username = username,
        avatarPath = avatarPath,
        rating = rating
    )
}