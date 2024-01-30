package com.github.faening.movieapp.data.api

import com.github.faening.movieapp.BuildConfig
import com.github.faening.movieapp.data.model.BasePaginationRemote
import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServiceApi {

    @GET("genre/movie/list")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun getAllGenres(
        @Query("language") language: String?,
    ): GenresResponse

    @GET("discover/movie")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun getAllMoviesByGenre(
        @Query("language") language: String?,
        @Query("with_genres") genreId: String?,
    ): BasePaginationRemote<List<MovieResponse>>

}