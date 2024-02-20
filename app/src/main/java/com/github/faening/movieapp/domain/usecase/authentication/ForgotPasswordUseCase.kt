package com.github.faening.movieapp.domain.usecase.authentication

import com.github.faening.movieapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String) {
        repository.forgotPassword(email)
    }
}