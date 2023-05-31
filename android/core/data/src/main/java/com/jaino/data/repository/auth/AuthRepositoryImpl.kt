package com.jaino.data.repository.auth

import com.jaino.datastore.BeJuRyuDatastore
import com.jaino.network.datasource.auth.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val source: AuthDataSource,
    private val datastore: BeJuRyuDatastore
) : AuthRepository {
    override suspend fun signInService(token: String) : Result<Unit> {
        runCatching { source.signIn(token).toSignIn() }
            .onSuccess {
                setAccessToken(it.accessToken)
                setRefreshToken(it.refreshToken)
                setUserId(it.userId)
                setNickName(it.nickName)
                return Result.success(Unit)
            }
            .onFailure{
                return Result.failure(it)
            }
        return Result.failure(Throwable("AuthRepositoryImpl UnKnown error"))
    }

    override fun setAccessToken(token: String) {
        datastore.accessToken = token
    }

    override fun setRefreshToken(token: String) {
        datastore.refreshToken = token
    }

    override fun setUserId(userId: Long) {
        datastore.userId = userId
    }

    override fun setNickName(nickName: String) {
        datastore.nickName = nickName
    }
}