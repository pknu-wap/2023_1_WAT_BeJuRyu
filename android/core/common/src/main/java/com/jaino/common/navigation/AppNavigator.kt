package com.jaino.common.navigation

import android.content.Intent

interface AppNavigator {
    fun navigateToAuth(): Intent

    fun navigateToAccount(): Intent

    fun navigateToHome(destination: String = ""): Intent
}