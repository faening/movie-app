package com.github.faening.movieapp.di

import com.github.faening.movieapp.data.api.ServiceApi
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
    fun provideServiceApi(serviceProvider: ServiceProvider) : ServiceApi {
        return serviceProvider.createService(ServiceApi::class.java)
    }
}