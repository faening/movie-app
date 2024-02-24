package com.github.faening.movieapp.domain.usecase.api.movie

import com.github.faening.movieapp.domain.model.MovieReview
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val respository: MovieRepository
) {
    suspend operator fun invoke(language: String?, movieId: Int): List<MovieReview> {
        return respository.fetchMovieReviews(
            language = language, movieId = movieId
        )
    }
}