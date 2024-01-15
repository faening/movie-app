package com.github.faening.movieapp.domain.usecase.auth

import com.github.faening.movieapp.domain.repository.auth.Auth
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val auth: Auth
) {
    suspend operator fun invoke(email: String) {
        auth.forgotPassword(email)
    }
}