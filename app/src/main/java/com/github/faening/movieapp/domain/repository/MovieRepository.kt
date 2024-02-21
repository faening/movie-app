package com.github.faening.movieapp.domain.repository

import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.model.MovieCredits

interface MovieRepository {
    suspend fun fetchMoviesByGenre(language: String?, genreId: Int?): List<Movie>
    suspend fun searchForMovies(language: String?, query: String): List<Movie>
    suspend fun fetchMovieDetails(movieId: Int, language: String?): Movie
    suspend fun fetchMovieCredits(movieId: Int, language: String?): MovieCredits
    suspend fun fetchSimilarMovies(movieId: Int, language: String?): List<Movie>
}