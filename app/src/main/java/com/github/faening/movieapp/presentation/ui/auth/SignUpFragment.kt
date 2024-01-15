package com.github.faening.movieapp.presentation.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentSignUpBinding
import com.github.faening.movieapp.presentation.viewmodel.SignUpViewModel
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
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
        buttonSignUpListener()
        progressLoadingListener()
    }

    private fun buttonSignUpListener() {
        val buttonSignUp = binding.signUpButtonRegister
        buttonSignUp.setOnClickListener { validateData() }
    }

    private fun progressLoadingListener() {
        val progressLoading = binding.signUpProgressLoading
        val progressLoadingImage = R.drawable.loading
        Glide.with(requireContext()).load(progressLoadingImage).into(progressLoading)
    }

    private fun validateData() {
        val email = binding.signUpEmail.text.toString().trim()
        val password = binding.signUpPassword.text.toString().trim()

        // TODO: Melhorar as validações do formulário posteriormente
        if (email.isNotEmpty() && password.isNotEmpty()) {
            signUpUser(email, password)
        }
    }

    private fun signUpUser(email: String, password: String) {
        viewModel.signUp(email, password).observe(viewLifecycleOwner) { stateView ->
            val progressLoading = binding.signUpProgressLoading

            when(stateView) {
                is StateView.Loading -> {
                    progressLoading.isVisible = true
                }
                is StateView.Success -> {
                    Toast.makeText(requireContext(), "Cadastro realizado com sucessso", Toast.LENGTH_SHORT).show()
                }
                is StateView.Error -> {
                    progressLoading.isVisible = false
                    Toast.makeText(requireContext(), stateView.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}