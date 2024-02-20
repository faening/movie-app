package com.github.faening.movieapp.data.repository.api

import com.github.faening.movieapp.data.api.GenreServiceApi
import com.github.faening.movieapp.data.mapper.toDomain
import com.github.faening.movieapp.domain.model.Genre
import com.github.faening.movieapp.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val service: GenreServiceApi
) : GenreRepository {
    override suspend fun fetchMovieGenres(language: String?): List<Genre> {
        return service.fetchMovieGenres(
            language = language
        ).genres?.map { it.toDomain() } ?: emptyList()
    }
}