package com.jaino.network.di

import com.jaino.network.datasource.analysis.AnalysisDataSource
import com.jaino.network.datasource.analysis.AnalysisDataSourceImpl
import com.jaino.network.datasource.auth.AuthDataSource
import com.jaino.network.datasource.auth.AuthDataSourceImpl
import com.jaino.network.datasource.dictionary.DrinkDataSource
import com.jaino.network.datasource.dictionary.DrinkDataSourceImpl
import com.jaino.network.datasource.rank.RankDataSource
import com.jaino.network.datasource.rank.RankDataSourceImpl
import com.jaino.network.datasource.review.ReviewDataSource
import com.jaino.network.datasource.review.ReviewDataSourceImpl
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
    abstract fun provideAuthDataSource(
        authDataSourceImpl : AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    @Singleton
    abstract fun provideDrinkDataSource(
        drinkDataSourceImpl: DrinkDataSourceImpl
    ): DrinkDataSource

    @Binds
    @Singleton
    abstract fun provideReviewDataSource(
        reviewDataSourceImpl: ReviewDataSourceImpl
    ): ReviewDataSource

    @Binds
    @Singleton
    abstract fun provideAnalysisSourceDataSource(
        analysisDataSourceImpl : AnalysisDataSourceImpl
    ): AnalysisDataSource

    @Binds
    @Singleton
    abstract fun provideRankDataSource(
        rankDataSourceImpl : RankDataSourceImpl
    ): RankDataSource
}