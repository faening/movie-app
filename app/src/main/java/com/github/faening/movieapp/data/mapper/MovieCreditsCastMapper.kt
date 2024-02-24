package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.MovieCreditsCastResponse
import com.github.faening.movieapp.domain.model.MovieCreditsCast

fun MovieCreditsCastResponse.toDomain(): MovieCreditsCast {
    return MovieCreditsCast(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
        castId = castId,
        character = character,
        creditId = creditId,
        order = order
    )
}