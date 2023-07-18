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
import com.jaino.data.repository.rank.RankRepository
import com.jaino.data.repository.rank.RankRepositoryImpl
import com.jaino.data.repository.setting.ProfileRepository
import com.jaino.data.repository.setting.ProfileRepositoryImpl
import com.jaino.datastore.TokenDataSource
import com.jaino.datastore.UserPreferencesDataSource
import com.jaino.network.datasource.analysis.AnalysisDataSource
import com.jaino.network.datasource.auth.AuthDataSource
import com.jaino.network.datasource.dictionary.DrinkDataSource
import com.jaino.network.datasource.rank.RankDataSource
import com.jaino.network.datasource.review.ReviewDataSource
import com.jaino.network.datasource.setting.ProfileDataSource
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
        authDataSource: AuthDataSource,
        TokenDataSource : TokenDataSource,
        userDataSource: UserPreferencesDataSource
    ) : AuthRepository = AuthRepositoryImpl(authDataSource, TokenDataSource, userDataSource)

    @Singleton
    @Provides
    fun provideLocalUserRepository(
        TokenDataSource : TokenDataSource,
        userPreferencesDataSource: UserPreferencesDataSource
    ): LocalUserRepository = LocalUserRepositoryImpl(TokenDataSource, userPreferencesDataSource)

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

    @Singleton
    @Provides
    fun provideRankRepository(
        RankDataSource: RankDataSource,
    ): RankRepository = RankRepositoryImpl(RankDataSource)

    @Singleton
    @Provides
    fun provideProfileRepository(
        profileDataSource: ProfileDataSource,
        userPreferencesDataSource: UserPreferencesDataSource
    ): ProfileRepository = ProfileRepositoryImpl(profileDataSource, userPreferencesDataSource)
}
