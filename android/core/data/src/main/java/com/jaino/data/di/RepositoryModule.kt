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
import com.jaino.network.datasource.dictionary.GetDrinkDataSource
import com.jaino.network.datasource.dictionary.GetDrinkListDataSource
import com.jaino.network.datasource.review.GetReviewListDataSource
import com.jaino.network.datasource.review.PostReviewDataSource
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
        getDrinkListDataSource : GetDrinkListDataSource,
        getDrinkDataSource : GetDrinkDataSource
    ): DrinksRepository = DrinksRepositoryImpl(getDrinkListDataSource, getDrinkDataSource)

    @Singleton
    @Provides
    fun provideReviewRepository(
        postReviewDataSource: PostReviewDataSource,
        getReviewListDataSource: GetReviewListDataSource
    ): ReviewRepository = ReviewRepositoryImpl(postReviewDataSource, getReviewListDataSource)
}
