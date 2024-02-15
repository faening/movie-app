package com.github.faening.movieapp.data.mapper

import com.github.faening.movieapp.data.model.CountryResponse
import com.github.faening.movieapp.data.model.GenreResponse
import com.github.faening.movieapp.data.model.MovieResponse
import com.github.faening.movieapp.domain.model.Country
import com.github.faening.movieapp.domain.model.Genre
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.ui.model.GenrePresentation

fun MovieResponse.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genres = genres?.map { it.toDomain() },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCountries = productionCountries?.map { it.toDomain() },
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

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

fun CountryResponse.toDomain(): Country {
    return Country(
        iso31661 = iso31661,
        name = name
    )
}