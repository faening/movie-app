package com.github.faening.movieapp.presentation.auth.sign_up

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
import com.github.faening.movieapp.databinding.FragmentSignUpBinding
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
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val model: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(binding.signUpToolbar)
        initializeListeners()
        setupProgressLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeListeners() {
        buttonSignUpListener()
        buttonAlreadyHaveAccountListener()
    }

    private fun buttonSignUpListener() {
        binding.signUpButtonRegister.setOnClickListener {
            val email = binding.signUpEmail.text.toString().trim()
            val password = binding.signUpPassword.text.toString().trim()
            val formIsValid = validateFormInputs(email, password)
            if (formIsValid) {
                hideKeyboard()
                signUpUser(email, password)
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

    private fun signUpUser(email: String, password: String) {
        model.signUp(email, password).observe(viewLifecycleOwner) { stateView ->
            val progressLoading = binding.signUpProgressLoading
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

    private fun buttonAlreadyHaveAccountListener() {
        binding.signUpButtonAlreadyHaveAccount.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupProgressLoading() {
        val progressLoading = binding.signUpProgressLoading
        val progressLoadingImage = R.drawable.loading
        Glide.with(requireContext()).load(progressLoadingImage).into(progressLoading)
    }

}