package com.jaino.network.di

import com.jaino.network.datasource.auth.SignInDataSource
import com.jaino.network.datasource.auth.SignInDataSourceImpl
import com.jaino.network.datasource.dictionary.GetDrinkDataSource
import com.jaino.network.datasource.dictionary.GetDrinkDataSourceImpl
import com.jaino.network.datasource.dictionary.GetDrinkListDataSource
import com.jaino.network.datasource.dictionary.GetDrinkListDataSourceImpl
import com.jaino.network.datasource.user.UserDataSource
import com.jaino.network.datasource.user.UserDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun provideUserDataSource(
        userDataSourceImpl : UserDataSourceImpl
    ): UserDataSource

    @Binds
    @Singleton
    abstract fun provideDrinkListDataSource(
        drinkListDataSourceImpl: GetDrinkListDataSourceImpl
    ): GetDrinkListDataSource

    @Binds
    @Singleton
    abstract fun provideDrinkDataSource(
        drinkDataSourceImpl: GetDrinkDataSourceImpl
    ): GetDrinkDataSource
}