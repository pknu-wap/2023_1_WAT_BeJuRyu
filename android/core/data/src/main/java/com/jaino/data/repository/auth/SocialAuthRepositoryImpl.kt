package com.jaino.data.repository.auth

import android.content.Context
import com.jaino.model.social_auth.KakaoToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SocialAuthRepositoryImpl @Inject constructor(
    private val kakaoClient: UserApiClient,
    @ActivityContext private val context: Context
) : SocialAuthRepository {

    override val isKakaoTalkLoginAvailable: Boolean
        get() = kakaoClient.isKakaoTalkLoginAvailable(context)

    override suspend fun signInByKakaoTalk(): Result<KakaoToken> =
        suspendCancellableCoroutine {
            kakaoClient.loginWithKakaoTalk(context){ token, error ->
                if (error != null) {
                    it.resume(Result.failure(error))
                    return@loginWithKakaoTalk
                }
                if (token != null) {
                    it.resume(Result.success(KakaoToken(token.accessToken)))
                    return@loginWithKakaoTalk
                }
                it.resumeWithException(Throwable("알 수 없는 오류가 발생하였습니다."))
            }
        }

    override suspend fun signInByKakaoAccount(): Result<KakaoToken> =
        suspendCancellableCoroutine {
            kakaoClient.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    it.resume(Result.failure(error))
                    return@loginWithKakaoAccount
                }
                if (token != null) {
                    it.resume(Result.success(KakaoToken(token.accessToken)))
                    return@loginWithKakaoAccount
                }
                it.resumeWithException(Throwable("알 수 없는 오류가 발생하였습니다."))
            }
        }

    override fun signOut() {
        kakaoClient.logout{
            Timber.e(it)
        }
    }
}