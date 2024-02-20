package com.github.faening.movieapp.domain.usecase.api.genre

import com.github.faening.movieapp.domain.model.Genre
import com.github.faening.movieapp.domain.repository.GenreRepository
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(
    private val repository: GenreRepository
) {
     suspend operator fun invoke(language: String?): List<Genre> {
         return repository.fetchMovieGenres(language)
     }
}