package com.github.faening.movieapp.data.repository.movie

import com.github.faening.movieapp.data.api.ServiceApi
import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieResponse
import com.github.faening.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val serviceApi: ServiceApi
) : MovieRepository {
    override suspend fun getAllGenres(language: String?): GenresResponse {
        return serviceApi.getAllGenres(
            language = language
        )
    }

    override suspend fun getAllMoviesByGenre(language: String?, genreId: Int?): List<MovieResponse> {
        return serviceApi.getAllMoviesByGenre(
            language = language,
            genreId = genreId
        ).results ?: emptyList()
    }
}