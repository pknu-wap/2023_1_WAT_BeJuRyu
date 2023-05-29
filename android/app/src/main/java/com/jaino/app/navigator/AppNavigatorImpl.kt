package com.jaino.app.navigator

import android.content.Context
import android.content.Intent
import com.jaino.account.AccountActivity
import com.jaino.app.HomeActivity
import com.jaino.auth.AuthActivity
import com.jaino.common.navigation.AppNavigator
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    @ApplicationContext private val context: Context
): AppNavigator {
    override fun navigateToAuth(): Intent = AuthActivity.getIntent(context)

    override fun navigateToAccount(): Intent = AccountActivity.getIntent(context)

    override fun navigateToHome(destination: String): Intent =
        HomeActivity.getIntent(context, destination)

}