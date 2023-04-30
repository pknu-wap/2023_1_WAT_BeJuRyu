package com.jaino.dictionary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DictionaryActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.dictionary_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}