package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.GenreResponse
import com.github.faening.movieapp.data.model.MovieResponse
import com.github.faening.movieapp.domain.model.Genre
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.presentation.model.GenrePresentation

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieResponse.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun Genre.toPresentation(): GenrePresentation {
    return GenrePresentation(
        id = id,
        name = name,
        movies = emptyList()
    )
}