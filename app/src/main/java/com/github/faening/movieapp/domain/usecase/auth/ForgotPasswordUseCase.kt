package com.github.faening.movieapp.domain.usecase.auth

import com.github.faening.movieapp.domain.repository.auth.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) {
        authRepository.forgotPassword(email)
    }
}