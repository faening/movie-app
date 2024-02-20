package com.github.faening.movieapp.di

import com.github.faening.movieapp.data.repository.api.GenreRepositoryImpl
import com.github.faening.movieapp.data.repository.authentication.firebase.FirebaseAuthenticationRepository
import com.github.faening.movieapp.data.repository.api.MovieRepositoryImpl
import com.github.faening.movieapp.domain.repository.AuthenticationRepository
import com.github.faening.movieapp.domain.repository.GenreRepository
import com.github.faening.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindAuthenticationRepository(repository: FirebaseAuthenticationRepository): AuthenticationRepository

    @Binds
    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindGenreRepository(repository: GenreRepositoryImpl): GenreRepository
}