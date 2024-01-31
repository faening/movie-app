package com.github.faening.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.faening.movieapp.data.mapper.toPresentation
import com.github.faening.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import com.github.faening.movieapp.domain.usecase.movie.GetAllGenresUseCase
import com.github.faening.movieapp.utils.Constants.Movie
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllGenresUseCase: GetAllGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : ViewModel() {
    fun getAllGenres() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val genres = getAllGenresUseCase.invoke(
                language = Movie.LANGUAGE
            ).map { it.toPresentation() }
            emit(StateView.Success(genres))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }

    fun getMoviesByGenre(genreId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = getMoviesByGenreUseCase.invoke(
                language = Movie.LANGUAGE,
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
}