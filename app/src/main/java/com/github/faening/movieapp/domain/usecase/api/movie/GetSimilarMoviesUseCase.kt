package com.github.faening.movieapp.domain.usecase.api.movie

import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val respository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int, language: String?,): List<Movie> {
        return respository.fetchSimilarMovies(
            movieId = movieId, language = language
        )
    }
}