package com.github.faening.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCountry(
    val iso31661: String?,
    val name: String?
) : Parcelable