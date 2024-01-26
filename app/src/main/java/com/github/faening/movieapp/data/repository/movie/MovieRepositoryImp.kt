package com.github.faening.movieapp.data.repository.movie

import com.github.faening.movieapp.data.api.ServiceApi
import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieResponse
import com.github.faening.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val serviceApi: ServiceApi
) : MovieRepository {
    override suspend fun getAllGenres(authorization: String, language: String?): GenresResponse {
        return serviceApi.getAllGenres(
            authorization = "Bearer $authorization",
            language = language
        )
    }

    override suspend fun getAllMoviesByGenre(authorization: String, language: String?, genreId: String?): List<MovieResponse> {
        return serviceApi.getAllMoviesByGenre(
            authorization = "Bearer $authorization",
            language = language,
            genreId = genreId
        ).results ?: emptyList()
    }
}