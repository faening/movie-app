package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.CastResponse
import com.github.faening.movieapp.domain.model.Cast

fun CastResponse.toDomain(): Cast {
    return Cast(
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