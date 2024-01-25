package com.github.faening.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?
)