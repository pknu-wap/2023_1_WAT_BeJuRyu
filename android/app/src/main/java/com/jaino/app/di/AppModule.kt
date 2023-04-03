package com.jaino.app.di

import android.app.Application
import com.kakao.sdk.user.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplication(application: Application) = application

    @Provides
    @Singleton
    fun provideKakaoClient(): UserApiClient = UserApiClient.instance

}