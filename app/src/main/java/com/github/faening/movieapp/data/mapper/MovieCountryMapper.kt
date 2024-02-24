package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.MovieProductionCountryResponse
import com.github.faening.movieapp.domain.model.MovieCountry

fun MovieProductionCountryResponse.toDomain(): MovieCountry {
    return MovieCountry(
        iso31661 = iso31661,
        name = name
    )
}