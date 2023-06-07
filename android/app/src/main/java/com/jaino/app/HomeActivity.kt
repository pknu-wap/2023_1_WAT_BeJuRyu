package com.jaino.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initFragmentContainer()
        navigateToDestination()
    }

    private fun initFragmentContainer(){
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
    private fun navigateToDestination(){
        val destination = intent.getStringExtra("DESTINATION") ?: return
        if(destination.isNotEmpty()){
            navController.navigate(destination.toUri())
        }
    }

    companion object {
        fun getIntent(context: Context, destination: String): Intent {
            return Intent(context, HomeActivity::class.java).apply {
                putExtra("DESTINATION", destination)
            }
        }
    }
}