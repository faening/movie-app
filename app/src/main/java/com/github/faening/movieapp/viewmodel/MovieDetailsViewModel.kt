package com.github.faening.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.faening.movieapp.domain.usecase.movie.GetMovieCreditsUseCase
import com.github.faening.movieapp.domain.usecase.movie.GetMovieDetailsUseCase
import com.github.faening.movieapp.utils.Constants
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase
) : ViewModel() {
    fun getMovieDetails(movieId: Int) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val movie = getMovieDetailsUseCase.invoke(
                language = Constants.Movie.LANGUAGE,
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

    fun getMovieCredits(movieId: Int) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val movieCredits = getMovieCreditsUseCase.invoke(
                language = Constants.Movie.LANGUAGE,
                movieId = movieId
            )
            emit(StateView.Success(movieCredits))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }
}