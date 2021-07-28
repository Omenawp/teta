package com.oelrun.teta

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oelrun.teta.moviedetail.MovieDetailsFragment
import com.oelrun.teta.movies.*
import com.oelrun.teta.profile.ProfileFragment

class MainActivity : AppCompatActivity(), MoviesFragmentClickListener {

    private lateinit var bottomNavMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavMenu = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        if (savedInstanceState == null) {
            bottomNavMenu.selectedItemId = R.id.to_home
            navigationChange(MoviesFragment())
        }

        bottomNavMenu.setOnNavigationItemSelectedListener {
            if (it.itemId != bottomNavMenu.selectedItemId) {
                if(it.itemId == R.id.to_profile){
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    navigationChange(ProfileFragment())
                } else {
                    navigationChange(MoviesFragment())
                }
            }
            true
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.fragments.size == 1 &&
            bottomNavMenu.selectedItemId == R.id.to_profile) {
            bottomNavMenu.selectedItemId = R.id.to_home
        } else {
            super.onBackPressed()
        }
    }

    override fun navigateToDetail(id: Int) {
        val stack = supportFragmentManager.fragments
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MovieDetailsFragment.newInstance(id))
            .hide(stack[0])
            .addToBackStack(null)
            .commit()
    }
}