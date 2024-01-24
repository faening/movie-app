package com.github.faening.movieapp.presentation.auth.sign_in

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentSignInBinding
import com.github.faening.movieapp.presentation.main.activity.MainActivity
import com.github.faening.movieapp.utils.FirebaseHelper
import com.github.faening.movieapp.utils.StateView
import com.github.faening.movieapp.utils.hideKeyboard
import com.github.faening.movieapp.utils.initializeToolbar
import com.github.faening.movieapp.utils.isEmailValid
import com.github.faening.movieapp.utils.isPasswordValid
import com.github.faening.movieapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val model: SignInViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(binding.signInToolbar)
        initializeListeners()
        setupProgressLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeListeners() {
        buttonSignInListener()
        buttonForgotPasswordListener()
    }

    private fun buttonSignInListener() {
        binding.signInButtonLogin.setOnClickListener {
            val email = binding.signInEmail.text.toString().trim()
            val password = binding.signInPassword.text.toString().trim()
            val formIsValid = validateFormInputs(email, password)
            if (formIsValid) {
                hideKeyboard()
                signInUser(email, password)
            }
        }
    }

    private fun validateFormInputs(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                showSnackBar(R.string.text_validation_email_empty)
                false
            }

            !email.isEmailValid() -> {
                showSnackBar(R.string.text_validation_email_invalid)
                false
            }

            password.isEmpty() -> {
                showSnackBar(R.string.text_validation_password_empty)
                false
            }

            !password.isPasswordValid() -> {
                showSnackBar(R.string.text_validation_password_invalid)
                false
            }

            else -> true
        }
    }

    private fun signInUser(email: String, password: String) {
        model.signIn(email, password).observe(viewLifecycleOwner) { stateView ->
            val progressLoading = binding.signInProgressLoading
            when (stateView) {
                is StateView.Loading -> {
                    progressLoading.isVisible = true
                }

                is StateView.Success -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }

                is StateView.Error -> {
                    val firebaseErrorMessage = FirebaseHelper.checkError(stateView.message.toString())
                    showSnackBar(firebaseErrorMessage)
                    progressLoading.isVisible = false
                }
            }
        }
    }

    private fun buttonForgotPasswordListener() {
        binding.signInForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }
    }

    private fun setupProgressLoading() {
        val progressLoading = binding.signInProgressLoading
        val progressLoadingImage = R.drawable.loading
        Glide.with(requireContext()).load(progressLoadingImage).into(progressLoading)
    }
}