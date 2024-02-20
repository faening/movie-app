package com.github.faening.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.faening.movieapp.domain.usecase.api.movie.GetMoviesByGenreUseCase
import com.github.faening.movieapp.domain.usecase.api.movie.SearchMoviesUseCase
import com.github.faening.movieapp.utils.Constants
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {
    fun getMoviesByGenre(genreId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = getMoviesByGenreUseCase.invoke(
                language = Constants.Movie.LANGUAGE,
                genreId = genreId
            )

            emit(StateView.Success(movies))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }

    fun searchMovies(query: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = searchMoviesUseCase.invoke(
                language = Constants.Movie.LANGUAGE,
                query = query
            )

            emit(StateView.Success(movies))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }
}