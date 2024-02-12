package com.github.faening.movieapp.domain.usecase.movie

import com.github.faening.movieapp.data.mapper.toDomain
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(language: String?, query: String): List<Movie> {
        return movieRepository.searchMovies(
            language = language, query = query
        ).filter { it.backdropPath != null }.map { it.toDomain() }
    }
}