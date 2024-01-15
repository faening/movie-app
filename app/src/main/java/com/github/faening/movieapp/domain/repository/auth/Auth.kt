package com.github.faening.movieapp.domain.repository.auth

interface Auth {
    suspend fun signIn(email: String, password: String)

    suspend fun signUp(email: String, password: String)

    suspend fun forgotPassword(email: String)
}