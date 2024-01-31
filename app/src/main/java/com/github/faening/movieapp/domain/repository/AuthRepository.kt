package com.github.faening.movieapp.domain.repository

interface AuthRepository {
    suspend fun signIn(email: String, password: String)

    suspend fun signUp(email: String, password: String)

    suspend fun forgotPassword(email: String)
}