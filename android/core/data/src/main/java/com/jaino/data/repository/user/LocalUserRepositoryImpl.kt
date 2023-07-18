package com.jaino.data.repository.user

import com.jaino.datastore.TokenDataSource
import com.jaino.datastore.UserPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val tokenDataSource : TokenDataSource,
    private val userPreferencesDataSource : UserPreferencesDataSource
) : LocalUserRepository{

    override fun getNickname(): Flow<String> = userPreferencesDataSource.userDataFlow.map{ it.nickname }

    override fun getUserId(): Flow<Long> = userPreferencesDataSource.userDataFlow.map{ it.userId }

    override fun clear(){
        tokenDataSource.clearToken()
    }
}