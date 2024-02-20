package com.github.faening.movieapp.data.api

import com.github.faening.movieapp.BuildConfig
import com.github.faening.movieapp.data.model.GenresResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GenreServiceApi {
    @GET("genre/movie/list")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun fetchMovieGenres(
        @Query("language") language: String?,
    ): GenresResponse
}