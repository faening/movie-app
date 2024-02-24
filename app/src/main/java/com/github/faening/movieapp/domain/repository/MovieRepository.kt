package com.github.faening.movieapp.domain.repository

import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.model.MovieCredits
import com.github.faening.movieapp.domain.model.MovieReview

interface MovieRepository {
    suspend fun fetchMoviesByGenre(language: String?, genreId: Int?): List<Movie>
    suspend fun searchForMovies(language: String?, query: String): List<Movie>
    suspend fun fetchMovieDetails(movieId: Int, language: String?): Movie
    suspend fun fetchMovieCredits(movieId: Int, language: String?): MovieCredits
    suspend fun fetchSimilarMovies(movieId: Int, language: String?): List<Movie>
    suspend fun fetchMovieReviews(movieId: Int, language: String?): List<MovieReview>
}