package com.jaino.data.repository.auth

import com.jaino.datastore.BeJuRyuDatastore
import com.jaino.network.datasource.auth.SignInDataSource
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val source: SignInDataSource,
    private val datastore: BeJuRyuDatastore
) : AuthRepository {
    override suspend fun signInService(token: String) : Result<Unit> {
        runCatching { source.signIn(token).toSignIn() }
            .onSuccess {
                setAccessToken(it.accessToken)
                setRefreshToken(it.refreshToken)
                setUserId(it.useId)
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

    override fun setUserId(userId: Int) {
        datastore.userId = userId
    }

    override fun setNickName(nickName: String) {
        datastore.nickName = nickName
    }

    override fun clear() {
        datastore.clear()
    }
}