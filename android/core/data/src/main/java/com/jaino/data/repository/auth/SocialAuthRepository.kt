package com.jaino.data.repository.auth

import com.jaino.data.model.auth.KakaoToken

interface SocialAuthRepository {

    val isKakaoTalkLoginAvailable: Boolean

    suspend fun signInByKakaoTalk(): Result<KakaoToken>

    suspend fun signInByKakaoAccount(): Result<KakaoToken>

    fun signOut()

}
