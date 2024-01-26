package com.github.faening.movieapp.domain.usecase.movie

import com.github.faening.movieapp.data.mapper.toDomain
import com.github.faening.movieapp.domain.model.Genre
import com.github.faening.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val movieRepository: MovieRepository) {
     suspend operator fun invoke(authorization: String, language: String?): List<Genre> {
         return movieRepository.getAllGenres(
             authorization = authorization,
             language = language
         ).genres?.map { it.toDomain() } ?: emptyList()
     }
}