package com.github.faening.movieapp.domain.usecase.api.movie

import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(language: String?, query: String): List<Movie> {
        return repository.searchForMovies(
            language = language, query = query
        )
    }
}