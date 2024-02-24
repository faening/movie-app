package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.MovieCreditsCrewResponse
import com.github.faening.movieapp.domain.model.MovieCreditsCrew

fun MovieCreditsCrewResponse.toDomain(): MovieCreditsCrew {
    return MovieCreditsCrew(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
        creditId = creditId,
        department = department,
        job = job
    )
}