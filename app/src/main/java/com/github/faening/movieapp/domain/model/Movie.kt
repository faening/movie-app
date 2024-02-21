package com.github.faening.movieapp.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    override val adult: Boolean?,
    override val backdropPath: String?,
    val genres: List<Genre>?,
    override val id: Int?,
    override val originalLanguage: String?,
    val originalTitle: String?,
    override val overview: String?,
    override val popularity: Float?,
    override val posterPath: String?,
    val productionCountries: List<Country>?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    override val voteAverage: Float?,
    override val voteCount: Int?
) : IParcelable, MediaItem(adult, backdropPath, id, originalLanguage, overview, popularity, posterPath, voteAverage, voteCount)
