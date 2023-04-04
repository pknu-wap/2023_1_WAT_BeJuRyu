package com.jaino.data.di

import com.jaino.data.repository.auth.AuthRepository
import com.jaino.data.repository.auth.AuthRepositoryImpl
import com.jaino.data.repository.auth.SocialAuthRepository
import com.jaino.data.repository.auth.SocialAuthRepositoryImpl
import com.jaino.datastore.BeJuRyuDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    // activity Scope not use singleton
    @Provides
    fun provideSocialAuthRepository(
        repositoryImpl : SocialAuthRepositoryImpl
    ) : SocialAuthRepository = repositoryImpl

    @Provides
    @Singleton
    fun provideAuthRepository(
        dataStore: BeJuRyuDatastore
    ) : AuthRepository = AuthRepositoryImpl(dataStore)
}
