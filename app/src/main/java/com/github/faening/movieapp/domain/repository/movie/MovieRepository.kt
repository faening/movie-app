package com.github.faening.movieapp.domain.repository.movie

import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieResponse

interface MovieRepository {
    suspend fun getAllGenres(language: String?): GenresResponse
    suspend fun getAllMoviesByGenre(language: String?, genreId: String?): List<MovieResponse>
}