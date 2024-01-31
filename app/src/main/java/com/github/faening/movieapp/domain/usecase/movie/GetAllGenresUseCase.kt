package com.github.faening.movieapp.domain.usecase.movie

import com.github.faening.movieapp.data.mapper.toDomain
import com.github.faening.movieapp.domain.model.Genre
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetAllGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
     suspend operator fun invoke(language: String?): List<Genre> {
         return movieRepository.getAllGenres(
             language = language
         ).genres?.map { it.toDomain() } ?: emptyList()
     }
}