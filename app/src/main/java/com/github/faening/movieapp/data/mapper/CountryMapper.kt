package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.CountryResponse
import com.github.faening.movieapp.domain.model.Country

fun CountryResponse.toDomain(): Country {
    return Country(
        iso31661 = iso31661,
        name = name
    )
}