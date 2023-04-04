package com.jaino.data.di

import com.jaino.data.repository.auth.SocialAuthRepository
import com.jaino.data.repository.auth.SocialAuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object SocialRepositoryModule {

    // activity Scope not use singleton
    @Provides
    fun provideSocialAuthRepository(
        repositoryImpl : SocialAuthRepositoryImpl
    ) : SocialAuthRepository = repositoryImpl

}
