package com.github.faening.movieapp.domain.model

abstract class MediaItem(
    open val adult: Boolean?,
    open val backdropPath: String?,
    open val id: Int?,
    open val originalLanguage: String?,
    open val overview: String?,
    open val popularity: Float?,
    open val posterPath: String?,
    open val voteAverage: Float?,
    open val voteCount: Int?
) : IParcelable {
    override fun hashCode(): Int {
        var result = adult.hashCode()
        result = 31 * result + backdropPath.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + originalLanguage.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + popularity.hashCode()
        result = 31 * result + posterPath.hashCode()
        result = 31 * result + voteAverage.hashCode()
        result = 31 * result + voteCount!!
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MediaItem

        if (adult != other.adult) return false
        if (backdropPath != other.backdropPath) return false
        if (id != other.id) return false
        if (originalLanguage != other.originalLanguage) return false
        if (overview != other.overview) return false
        if (popularity != other.popularity) return false
        if (posterPath != other.posterPath) return false
        if (voteAverage != other.voteAverage) return false
        if (voteCount != other.voteCount) return false

        return true
    }
}