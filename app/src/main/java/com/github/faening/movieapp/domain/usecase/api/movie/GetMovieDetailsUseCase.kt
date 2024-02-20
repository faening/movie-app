package com.github.faening.movieapp.domain.usecase.api.movie

import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(language: String?, movieId: Int): Movie {
        return repository.fetchMovieDetails(
            language = language, movieId = movieId
        )
    }
}