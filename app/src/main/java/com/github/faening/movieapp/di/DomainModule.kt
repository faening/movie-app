package com.github.faening.movieapp.di

import com.github.faening.movieapp.data.repository.auth.AuthRepositoryImp
import com.github.faening.movieapp.data.repository.movie.MovieRepositoryImp
import com.github.faening.movieapp.domain.repository.AuthRepository
import com.github.faening.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindsAuthRepositoryImp(authRepositoryImp: AuthRepositoryImp): AuthRepository

    @Binds
    abstract fun bindsMovieRepositoryImp(movieRepositoryImp: MovieRepositoryImp): MovieRepository
}