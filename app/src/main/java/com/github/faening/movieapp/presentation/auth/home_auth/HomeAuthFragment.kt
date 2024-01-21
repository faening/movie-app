package com.github.faening.movieapp.presentation.auth.home_auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentHomeAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAuthFragment : Fragment() {

    private var _binding: FragmentHomeAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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