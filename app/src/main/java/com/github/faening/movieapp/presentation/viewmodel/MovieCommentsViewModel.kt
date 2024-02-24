package com.github.faening.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.faening.movieapp.domain.usecase.api.movie.GetMovieReviewsUseCase
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieCommentsViewModel @Inject constructor(
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase
) : ViewModel() {
    fun getMovieReviews(movieId: Int) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            /**
             * Neste caso em específico, a language é fixa por conta da baixa quantidade de reviews em português.
             */
            val movie = getMovieReviewsUseCase.invoke(
                language = "en-US",
                movieId = movieId
            )
            emit(StateView.Success(movie))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }

}