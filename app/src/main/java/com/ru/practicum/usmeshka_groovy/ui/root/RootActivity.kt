package com.ru.practicum.usmeshka_groovy.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.practicum.usmeshka_groovy.R
import com.ru.practicum.usmeshka_groovy.util.SplashFragment

class RootActivity : AppCompatActivity() {
    private var isChild = true
    private lateinit var childNavBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity)

        childNavBar = findViewById(R.id.bottomNavigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationGroup = findViewById<Group>(R.id.bottomNavigationGroup)
        childNavBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    bottomNavigationGroup.visibility = View.GONE
                }
                R.id.registrationFragment -> {
                    bottomNavigationGroup.visibility = View.GONE
                }
                else -> {
                    bottomNavigationGroup.visibility = View.VISIBLE
                }
            }
        }
        childNavBar.visibility = View.VISIBLE
    }

    fun switchNavBar(isChild: Boolean) {
        childNavBar.menu.clear()
        if (isChild) {
            menuInflater.inflate(R.menu.bottom_navigation_menu, childNavBar.menu)
        } else {
            menuInflater.inflate(R.menu.bottom_navigation_menu_parent, childNavBar.menu)
        }
    }
}