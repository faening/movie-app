package com.github.faening.movieapp.data.api

import com.github.faening.movieapp.data.model.BasePaginationRemote
import com.github.faening.movieapp.data.model.GenresResponse
import com.github.faening.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("genre/movie/list")
    suspend fun getAllGenres(
        @Query("authorization") authorization: String,
        @Query("language") language: String?,
    ) : GenresResponse

    @GET("discover/movie")
    suspend fun getAllMoviesByGenre(
        @Query("authorization") authorization: String,
        @Query("language") language: String?,
        @Query("with_genres") genreId: String?,
    ) : BasePaginationRemote<List<MovieResponse>>

}