package com.github.faening.movieapp.presentation.auth.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.ActivityAuthBinding
import com.github.faening.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}