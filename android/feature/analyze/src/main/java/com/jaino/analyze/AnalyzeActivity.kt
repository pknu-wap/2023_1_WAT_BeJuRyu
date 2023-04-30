package com.jaino.analyze

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalyzeActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val viewModel : AnalyzeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.analyze_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}