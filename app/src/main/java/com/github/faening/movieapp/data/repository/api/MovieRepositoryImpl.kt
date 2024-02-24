package com.github.faening.movieapp.data.repository.api

import com.github.faening.movieapp.data.api.MovieServiceApi
import com.github.faening.movieapp.data.mapper.toDomain
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.model.MovieCredits
import com.github.faening.movieapp.domain.model.MovieReview
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieServiceApi
) : MovieRepository {
    override suspend fun fetchMoviesByGenre(language: String?, genreId: Int?): List<Movie> {
        return service.fetchMoviesByGenre(
            language = language,
            genreId = genreId
        ).results?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun searchForMovies(language: String?, query: String): List<Movie> {
        return service.searchForMovies(
            language = language,
            query = query
        ).results?.filter { it.backdropPath != null }?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun fetchMovieDetails(movieId: Int, language: String?): Movie {
        return service.fetchMovieDetails(
            movieId = movieId,
            language = language
        ).toDomain()
    }

    override suspend fun fetchMovieCredits(movieId: Int, language: String?): MovieCredits {
        return service.fetchMovieCredits(
            movieId = movieId,
            language = language
        ).toDomain()
    }

    override suspend fun fetchSimilarMovies(movieId: Int, language: String?): List<Movie> {
        return service.fetchSimilarMovies(
            movieId = movieId,
            language = language
        ).results?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun fetchMovieReviews(movieId: Int, language: String?): List<MovieReview> {
        return service.fetchMovieReviews(
            movieId = movieId,
            language = language
        ).results?.map { it.toDomain() } ?: emptyList()
    }
}