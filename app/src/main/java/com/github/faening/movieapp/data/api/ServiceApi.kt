package com.github.faening.movieapp.data.api

import com.github.faening.movieapp.BuildConfig
import com.github.faening.movieapp.data.model.BasePaginationResponse
import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieCreditsResponse
import com.github.faening.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
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
        @Query("with_genres") genreId: Int?,
    ): BasePaginationResponse<List<MovieResponse>>

    @GET("search/movie")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun searchMovies(
        @Query("language") language: String?,
        @Query("query") query: String,
    ): BasePaginationResponse<List<MovieResponse>>

    @GET("movie/{movie_id}")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String?
    ): MovieResponse

    @GET("movie/{movie_id}/credits")
    @Headers("Authorization: Bearer ${BuildConfig.TMBD_API_TOKEN}")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String?
    ): MovieCreditsResponse
}