package com.github.faening.movieapp.domain.repository

import com.github.faening.movieapp.domain.model.Genre

interface GenreRepository {
    suspend fun fetchMovieGenres(language: String?): List<Genre>
}