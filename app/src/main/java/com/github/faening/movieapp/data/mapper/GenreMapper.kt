package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.GenreResponse
import com.github.faening.movieapp.domain.model.Genre
import com.github.faening.movieapp.ui.model.GenrePresentation

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun Genre.toPresentation(): GenrePresentation {
    return GenrePresentation(
        id = id,
        name = name,
        movies = emptyList()
    )
}