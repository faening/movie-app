package com.github.faening.movieapp.domain.repository

import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieCreditsResponse
import com.github.faening.movieapp.data.model.MovieResponse

interface MovieRepository {
    suspend fun getAllGenres(language: String?): GenresResponse
    suspend fun getAllMoviesByGenre(language: String?, genreId: Int?): List<MovieResponse>
    suspend fun searchMovies(language: String?, query: String): List<MovieResponse>
    suspend fun getMovieDetails(movieId: Int, language: String?): MovieResponse
    suspend fun getMovieCredits(movieId: Int, language: String?): MovieCreditsResponse
}