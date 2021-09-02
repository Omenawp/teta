package com.oelrun.teta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oelrun.teta.screens.movies.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavMenu: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavMenu = findViewById(R.id.bottom_navigation)
        navController = this.findNavController(R.id.nav_host_fragment)
        bottomNavMenu.setupWithNavController(navController)

        bottomNavMenu.setOnItemReselectedListener {
            if(it.itemId == R.id.moviesFragment && navController.backQueue.size > 2) {
                navController.popBackStack()
            }
        }
    }

    override fun onBackPressed() {
        if(bottomNavMenu.selectedItemId == R.id.profileFragment) {
            bottomNavMenu.selectedItemId = R.id.moviesFragment
        } else {
            super.onBackPressed()
        }
    }
}