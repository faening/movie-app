package com.github.faening.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso31661: String?,

    @SerializedName("name")
    val name: String?
)
