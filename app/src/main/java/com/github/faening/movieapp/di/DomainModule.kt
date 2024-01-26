package com.github.faening.movieapp.di

import com.github.faening.movieapp.data.repository.auth.AuthRepositoryImp
import com.github.faening.movieapp.domain.repository.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindFirebaseAuth(authRepositoryImp: AuthRepositoryImp) : AuthRepository
}