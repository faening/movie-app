package com.github.faening.movieapp.domain.usecase.api.movie

import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(language: String?, genreId: Int?): List<Movie> {
        return repository.fetchMoviesByGenre(
            language = language, genreId = genreId
        )
    }
}