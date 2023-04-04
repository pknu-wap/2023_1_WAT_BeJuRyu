package com.jaino.network.di

import com.jaino.network.datasource.auth.AuthDataSource
import com.jaino.network.datasource.auth.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun provideAuthDataSource(
        authDataSourceImpl : AuthDataSourceImpl
    ): AuthDataSource

}