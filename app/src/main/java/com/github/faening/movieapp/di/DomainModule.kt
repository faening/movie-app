package com.github.faening.movieapp.di

import com.github.faening.movieapp.data.firebase.repository.auth.FirebaseAuth
import com.github.faening.movieapp.domain.repository.auth.Auth
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindFirebaseAuth(firebaseAuth: FirebaseAuth) : Auth
}