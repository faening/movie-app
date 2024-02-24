package com.github.faening.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCredits(
    val id: Int?,
    val movieCreditsCast: List<MovieCreditsCast>?,
    val movieCreditsCrew: List<MovieCreditsCrew>?
) : Parcelable
