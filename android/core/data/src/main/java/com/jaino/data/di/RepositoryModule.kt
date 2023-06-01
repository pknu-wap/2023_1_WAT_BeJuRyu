package com.jaino.data.di

import com.jaino.data.repository.analysis.AnalysisRepository
import com.jaino.data.repository.auth.AuthRepository
import com.jaino.data.repository.auth.AuthRepositoryImpl
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.data.repository.dictionary.DrinksRepositoryImpl
import com.jaino.data.repository.review.ReviewRepository
import com.jaino.data.repository.review.ReviewRepositoryImpl
import com.jaino.data.repository.user.LocalUserRepository
import com.jaino.data.repository.user.LocalUserRepositoryImpl
import com.jaino.data.repository.analysis.AnalysisRepositoryImpl
import com.jaino.datastore.BeJuRyuDatastore
import com.jaino.network.datasource.analysis.AnalysisDataSource
import com.jaino.network.datasource.auth.AuthDataSource
import com.jaino.network.datasource.dictionary.DrinkDataSource
import com.jaino.network.datasource.review.ReviewDataSource
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
        dataSource: AuthDataSource,
        dataStore : BeJuRyuDatastore
    ) : AuthRepository = AuthRepositoryImpl(dataSource, dataStore)

    @Singleton
    @Provides
    fun provideLocalUserRepository(
        dataStore: BeJuRyuDatastore
    ): LocalUserRepository = LocalUserRepositoryImpl(dataStore)

    @Singleton
    @Provides
    fun provideAnalysisRepository(
        source : AnalysisDataSource
    ): AnalysisRepository = AnalysisRepositoryImpl(source)


    @Singleton
    @Provides
    fun provideDrinkRepository(
        drinkDataSource : DrinkDataSource,
    ): DrinksRepository = DrinksRepositoryImpl(drinkDataSource)

    @Singleton
    @Provides
    fun provideReviewRepository(
        reviewDataSource: ReviewDataSource,
    ): ReviewRepository = ReviewRepositoryImpl(reviewDataSource)
}
