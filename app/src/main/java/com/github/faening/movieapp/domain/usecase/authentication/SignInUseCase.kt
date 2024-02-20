package com.github.faening.movieapp.domain.usecase.authentication

import com.github.faening.movieapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.signIn(email, password)
    }
}