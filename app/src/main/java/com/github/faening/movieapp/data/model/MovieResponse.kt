package com.github.faening.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("genres")
    val genres: List<GenreResponse>?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Float?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("production_countries")
    val productionCountries: List<MovieProductionCountryResponse>?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Float?,

    @SerializedName("vote_count")
    val voteCount: Int?
)