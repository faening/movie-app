package com.github.faening.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCreditsCast(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    val knownForDepartment: String?,
    val name: String?,
    val originalName: String?,
    val popularity: Double?,
    val profilePath: String?,
    val castId: Int?,
    val character: String?,
    val creditId: String?,
    val order: Int?
) : Parcelable
