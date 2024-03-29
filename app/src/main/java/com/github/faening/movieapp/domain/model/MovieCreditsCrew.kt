package com.github.faening.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCreditsCrew(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    val knownForDepartment: String?,
    val name: String?,
    val originalName: String?,
    val popularity: Double?,
    val profilePath: String?,
    val creditId: String?,
    val department: String?,
    val job: String?
) : Parcelable
