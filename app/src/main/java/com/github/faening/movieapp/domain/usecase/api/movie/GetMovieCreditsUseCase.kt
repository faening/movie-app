package com.github.faening.movieapp.domain.usecase.api.movie

import com.github.faening.movieapp.domain.model.MovieCredits
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val respository: MovieRepository
) {
    suspend operator fun invoke(language: String?, movieId: Int): MovieCredits {
        return respository.fetchMovieCredits(
            language = language, movieId = movieId
        )
    }
}