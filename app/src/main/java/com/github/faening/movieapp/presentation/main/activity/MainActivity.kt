package com.github.faening.movieapp.presentation.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.github.faening.movieapp.BuildConfig
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // installSplashScreen()
        initializeNavigation()
        BuildConfig.TMBD_API_KEY
    }

    private fun initializeNavigation() {
        val mainFragmentContainerId = R.id.main_fragment_container
        val navHostFragment = supportFragmentManager.findFragmentById(mainFragmentContainerId) as NavHostFragment

        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.mainBottombarMenu, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.mainBottombarMenu.isVisible =
                destination.id == R.id.menu_home ||
                        destination.id == R.id.menu_search ||
                        destination.id == R.id.menu_favorite ||
                        destination.id == R.id.menu_download ||
                        destination.id == R.id.menu_profile
        }
    }


}