package com.github.faening.movieapp.domain.usecase.movie

import com.github.faening.movieapp.data.mapper.toDomain
import com.github.faening.movieapp.domain.model.Movie
import com.github.faening.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(private val movieRepository: MovieRepository) {
     suspend operator fun invoke(language: String?, genreId: String?): List<Movie> {
         return movieRepository.getAllMoviesByGenre(
             language = language,
             genreId = genreId
         ).map { it.toDomain() }
     }
}