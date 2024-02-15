package com.github.faening.movieapp.domain.model

import android.os.Parcelable
import com.github.faening.movieapp.data.model.CountryResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean?,
    val backdropPath: String?,
    val genres: List<Genre>?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float?,
    val posterPath: String?,
    val productionCountries: List<Country>?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val voteCount: Int?
): Parcelable
