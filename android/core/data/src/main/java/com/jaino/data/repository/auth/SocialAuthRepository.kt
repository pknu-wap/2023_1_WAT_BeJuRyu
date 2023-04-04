package com.jaino.data.repository.auth

import com.jaino.model.social_auth.KakaoToken

interface SocialAuthRepository {

    val isKakaoTalkLoginAvailable: Boolean

    suspend fun signInByKakaoTalk(): Result<KakaoToken>

    suspend fun signInByKakaoAccount(): Result<KakaoToken>

    fun signOut()

}
