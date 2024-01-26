package com.github.faening.movieapp.domain.repository.movie

import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieResponse

interface MovieRepository {
    suspend fun getAllGenres(authorization: String, language: String?): GenresResponse
    suspend fun getAllMoviesByGenre(authorization: String, language: String?, genreId: String?): List<MovieResponse>
}