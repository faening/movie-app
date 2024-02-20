package com.github.faening.movieapp.di

import com.github.faening.movieapp.data.api.GenreServiceApi
import com.github.faening.movieapp.data.api.MovieServiceApi
import com.github.faening.movieapp.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideServiceProvider() = ServiceProvider()

    @Provides
    fun provideMovieServiceApi(service: ServiceProvider) : MovieServiceApi {
        return service.createService(MovieServiceApi::class.java)
    }

    @Provides
    fun provideGenreServiceApi(service: ServiceProvider) : GenreServiceApi {
        return service.createService(GenreServiceApi::class.java)
    }
}