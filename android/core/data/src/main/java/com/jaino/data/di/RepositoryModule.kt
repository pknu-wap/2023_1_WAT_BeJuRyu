package com.jaino.data.di

import com.jaino.data.repository.auth.AuthRepository
import com.jaino.data.repository.auth.AuthRepositoryImpl
import com.jaino.datastore.BeJuRyuDatastore
import com.jaino.network.datasource.auth.SignInDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSocialAuthRepository(
        dataSource: SignInDataSource,
        dataStore : BeJuRyuDatastore
    ) : AuthRepository = AuthRepositoryImpl(dataSource, dataStore)

}
