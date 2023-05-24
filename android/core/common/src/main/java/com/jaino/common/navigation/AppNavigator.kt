package com.jaino.common.navigation

import android.content.Intent

interface AppNavigator {
    fun navigateToAuth(): Intent

    fun navigateToSetting(): Intent

    fun navigateToHome(): Intent
}