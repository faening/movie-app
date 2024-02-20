package com.github.faening.movieapp.data.api

import com.github.faening.movieapp.BuildConfig
import com.github.faening.movieapp.data.model.BasePaginationResponse
import com.github.faening.movieapp.data.model.MovieCreditsResponse
import com.github.faening.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServiceApi {
    @GET("discover/movie")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun fetchMoviesByGenre(
        @Query("language") language: String?,
        @Query("with_genres") genreId: Int?,
    ): BasePaginationResponse<List<MovieResponse>>

    @GET("search/movie")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun searchForMovies(
        @Query("language") language: String?,
        @Query("query") query: String,
    ): BasePaginationResponse<List<MovieResponse>>

    @GET("movie/{movie_id}")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String?
    ): MovieResponse

    @GET("movie/{movie_id}/credits")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun fetchMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String?
    ): MovieCreditsResponse
}