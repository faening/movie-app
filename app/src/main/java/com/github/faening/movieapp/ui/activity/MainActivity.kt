package com.github.faening.movieapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy {(
        supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        ).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // installSplashScreen()
        initializeNavigation()
    }

    private fun initializeNavigation() {
        NavigationUI.setupWithNavController(binding.mainBottombarMenu, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.mainBottombarMenu.isVisible =
                destination.id == R.id.menu_home ||
                        destination.id == R.id.menu_search ||
                        destination.id == R.id.menu_favorite ||
                        destination.id == R.id.menu_download ||
                        destination.id == R.id.menu_profile
        }
    }
}