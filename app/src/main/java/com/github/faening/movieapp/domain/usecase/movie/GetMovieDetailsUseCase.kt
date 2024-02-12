package com.github.faening.movieapp.domain.usecase.movie

import com.github.faening.movieapp.data.mapper.toDomain
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(language: String?, movieId: Int): Movie {
        return movieRepository.getMovieDetails(
            language = language, movieId = movieId
        ).toDomain()
    }
}