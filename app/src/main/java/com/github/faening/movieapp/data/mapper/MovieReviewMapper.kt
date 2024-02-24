package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.MovieReviewResponse
import com.github.faening.movieapp.domain.model.MovieReview

fun MovieReviewResponse.toDomain(): MovieReview {
    return MovieReview(
        author = author,
        authorDetails = authorDetails?.toDomain(),
        content = content,
        createdAt = createdAt,
        id = id,
        updatedAt = updatedAt,
        url = url
    )
}