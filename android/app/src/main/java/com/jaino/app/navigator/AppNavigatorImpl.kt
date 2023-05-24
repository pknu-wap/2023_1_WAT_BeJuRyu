package com.jaino.app.navigator

import android.content.Context
import android.content.Intent
import com.jaino.app.HomeActivity
import com.jaino.auth.AuthActivity
import com.jaino.common.navigation.AppNavigator
import com.jaino.setting.SettingActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    @ApplicationContext private val context: Context
): AppNavigator {
    override fun navigateToAuth(): Intent = Intent(context, AuthActivity::class.java)

    override fun navigateToSetting(): Intent = Intent(context, SettingActivity::class.java)

    override fun navigateToHome(): Intent = Intent(context, HomeActivity::class.java)

}