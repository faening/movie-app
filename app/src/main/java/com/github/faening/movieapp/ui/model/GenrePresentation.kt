package com.github.faening.movieapp.ui.model

import android.os.Parcelable
import com.github.faening.movieapp.domain.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenrePresentation(
    val id: Int?,
    val name: String?,
    val movies: List<Movie>?
) : Parcelable
