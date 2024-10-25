package com.github.faening.movieapp.presentation.view.fragment.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentHomeAuthBinding
import com.github.faening.movieapp.presentation.view.screen.HomeAuthScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAuthFragment : Fragment() {

    private val binding by lazy { FragmentHomeAuthBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // return binding.root
        return ComposeView(requireContext()).apply {
            setContent {
                HomeAuthScreen(navController = findNavController())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initializeListeners()
    }

    private fun initializeListeners() {
        buttonLoginWithAccountListener()
        buttonDontHaveAccountListener()
    }

    private fun buttonLoginWithAccountListener() {
        binding.homeAuthButtonLoginWithAccount.setOnClickListener {
            findNavController().navigate(R.id.action_homeAuthFragment_to_signInFragment)
        }
    }

    private fun buttonDontHaveAccountListener() {
        binding.homeAuthButtonDontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_homeAuthFragment_to_signUpFragment)
        }
    }

}