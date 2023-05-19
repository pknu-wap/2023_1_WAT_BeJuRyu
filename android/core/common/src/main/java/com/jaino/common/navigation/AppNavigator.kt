package com.jaino.common.navigation

import android.content.Intent

interface AppNavigator {
    fun navigateToAuth(): Intent

    fun navigateToSetting(): Intent

    fun navigateToAnalyze(): Intent

    fun navigateToDictionary(): Intent

    fun navigateToReview(): Intent
}