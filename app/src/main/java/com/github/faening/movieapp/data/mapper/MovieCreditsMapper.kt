package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.MovieCreditsResponse
import com.github.faening.movieapp.domain.model.MovieCredits

fun MovieCreditsResponse.toDomain(): MovieCredits {
    return MovieCredits(
        id = id,
        movieCreditsCast = cast?.map { it.toDomain() },
        movieCreditsCrew = crew?.map { it.toDomain() }
    )
}