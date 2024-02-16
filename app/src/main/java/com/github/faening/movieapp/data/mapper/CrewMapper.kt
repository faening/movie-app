package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.CrewResponse
import com.github.faening.movieapp.domain.model.Crew

fun CrewResponse.toDomain(): Crew {
    return Crew(
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