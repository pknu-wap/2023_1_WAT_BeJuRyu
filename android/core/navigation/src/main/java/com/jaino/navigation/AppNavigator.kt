package com.jaino.navigation

import android.content.Intent

interface AppNavigator {
    fun navigateToAuth(): Intent
    fun navigateToSetting(): Intent

    fun navigateToAnalyze(): Intent
}