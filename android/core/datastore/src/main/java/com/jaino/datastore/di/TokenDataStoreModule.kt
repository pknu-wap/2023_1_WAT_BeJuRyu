package com.jaino.datastore.di

import com.jaino.datastore.TokenDataSource
import com.jaino.datastore.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenDataStoreModule {

    @Binds
    @Singleton
    abstract fun provideTokenDataSource(
        tokenDataSourceImpl: TokenDataSourceImpl
    ): TokenDataSource
}