package com.jaino.network.di

import com.jaino.network.datasource.auth.SignInDataSource
import com.jaino.network.datasource.auth.SignInDataSourceImpl
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
    abstract fun provideSignInDataSource(
        signInDataSourceImpl : SignInDataSourceImpl
    ): SignInDataSource

}