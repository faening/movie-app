package com.github.faening.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("cast")
    val cast: List<MovieCreditsCastResponse>?,

    @SerializedName("crew")
    val crew: List<MovieCreditsCrewResponse>?
)