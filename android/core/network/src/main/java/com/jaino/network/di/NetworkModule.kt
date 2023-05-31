package com.jaino.network.di

import com.jaino.network.datasource.analysis.AnalysisDataSource
import com.jaino.network.datasource.analysis.AnalysisDataSourceImpl
import com.jaino.network.datasource.auth.AuthDataSource
import com.jaino.network.datasource.auth.SignInDataSourceImpl
import com.jaino.network.datasource.dictionary.GetDrinkDataSource
import com.jaino.network.datasource.dictionary.GetDrinkDataSourceImpl
import com.jaino.network.datasource.dictionary.GetDrinkListDataSource
import com.jaino.network.datasource.dictionary.GetDrinkListDataSourceImpl
import com.jaino.network.datasource.review.GetReviewListDataSource
import com.jaino.network.datasource.review.GetReviewListDataSourceImpl
import com.jaino.network.datasource.review.PostReviewDataSource
import com.jaino.network.datasource.review.PostReviewDataSourceImpl
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
    ): AuthDataSource

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

    @Binds
    @Singleton
    abstract fun provideGetReviewListDataStore(
        reviewListDataSourceImpl: GetReviewListDataSourceImpl
    ): GetReviewListDataSource

    @Binds
    @Singleton
    abstract fun providePostReviewDataSource(
        postReviewDataSourceImpl: PostReviewDataSourceImpl
    ): PostReviewDataSource

    @Binds
    @Singleton
    abstract fun providePostAnalysisSourceDataSource(
        analysisDataSourceImpl : AnalysisDataSourceImpl
    ): AnalysisDataSource
}