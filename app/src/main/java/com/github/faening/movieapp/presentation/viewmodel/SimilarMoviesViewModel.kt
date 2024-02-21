package com.github.faening.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.faening.movieapp.domain.usecase.api.movie.GetSimilarMoviesUseCase
import com.github.faening.movieapp.utils.Constants
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {
    fun getSimilarMovies(movieId: Int) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val similarMovies = getSimilarMoviesUseCase.invoke(
                language = Constants.Movie.LANGUAGE,
                movieId = movieId
            )
            emit(StateView.Success(similarMovies))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }
}