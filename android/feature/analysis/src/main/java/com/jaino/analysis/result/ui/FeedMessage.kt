package com.jaino.analysis.result.ui

import com.kakao.sdk.template.model.Button
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.Link

object FeedMessage {
    fun createTemplate(): FeedTemplate {
        return FeedTemplate(
            content = Content(
                title = "Be주류",
                description = "사용자의 감정을 인공지능이 분석해서 주류를 추천해요!",
                imageUrl = "https://github.com/pknu-wap/2023_1_WAT_BeJuRyu/raw/develop_android/image/icon.png?raw=true",
                link = Link()
            ),
            buttons = listOf(
                Button(
                    title = "주류 추천 받으러 가기",
                    link = Link()
                )
            )
        )
    }
}