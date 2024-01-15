package com.github.faening.movieapp.presentation.auth.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.faening.movieapp.domain.usecase.auth.SignInUseCase
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            signInUseCase.invoke(email, password)
            emit(StateView.Success(Unit))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message =  exception.message))
        }
    }
}