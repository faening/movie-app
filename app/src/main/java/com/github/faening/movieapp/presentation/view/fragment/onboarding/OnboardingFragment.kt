package com.github.faening.movieapp.presentation.view.fragment.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private val binding by lazy { FragmentOnboardingBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
    }

    private fun initializeListeners() {
        buttonLoginListener()
    }

    private fun buttonLoginListener() {
        binding.onboardButtonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_navigation)
        }
    }
}