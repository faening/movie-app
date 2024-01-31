package com.github.faening.movieapp.domain.repository

import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieResponse

interface MovieRepository {
    suspend fun getAllGenres(language: String?): GenresResponse
    suspend fun getAllMoviesByGenre(language: String?, genreId: Int?): List<MovieResponse>
}