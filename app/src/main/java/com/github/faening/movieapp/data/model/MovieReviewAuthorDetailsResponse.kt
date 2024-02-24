package com.github.faening.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieReviewAuthorDetailsResponse(
    @SerializedName("name")
    val name: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("avatar_path")
    val avatarPath: String?,

    @SerializedName("rating")
    val rating: Float?
)
