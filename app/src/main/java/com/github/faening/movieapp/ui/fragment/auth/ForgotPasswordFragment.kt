package com.github.faening.movieapp.ui.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentForgotPasswordBinding
import com.github.faening.movieapp.utils.FirebaseHelper
import com.github.faening.movieapp.utils.StateView
import com.github.faening.movieapp.utils.hideKeyboard
import com.github.faening.movieapp.utils.initializeToolbar
import com.github.faening.movieapp.utils.isEmailValid
import com.github.faening.movieapp.utils.showSnackBar
import com.github.faening.movieapp.viewmodel.ForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private val binding by lazy { FragmentForgotPasswordBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ForgotPasswordViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(binding.forgotPasswordToolbar)
        initializeListeners()
        setupProgressLoading()
    }

    private fun initializeListeners() {
        buttonRetrieveAccountListener()
    }

    private fun buttonRetrieveAccountListener() {
        binding.forgotPasswordButtonRetrieveAccount.setOnClickListener {
            val email = binding.forgotPasswordEmail.text.toString().trim()
            val formIsValid = validateFormInput(email)
            if (formIsValid) {
                hideKeyboard()
                retrieveAccount(email)
            }
        }
    }

    private fun validateFormInput(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                showSnackBar(R.string.text_validation_email_empty)
                false
            }

            !email.isEmailValid() -> {
                showSnackBar(R.string.text_validation_email_invalid)
                false
            }

            else -> true
        }
    }

    private fun retrieveAccount(email: String) {
        viewModel.forgotPassword(email).observe(viewLifecycleOwner) { stateView ->
            val progressLoading = binding.forgotPasswordProgressLoading
            when (stateView) {
                is StateView.Loading -> {
                    progressLoading.isVisible = true
                }

                is StateView.Success -> {
                    showSnackBar(R.string.forgot_password_fragment_email_recovery_sent)
                }

                is StateView.Error -> {
                    val firebaseErrorMessage = FirebaseHelper.checkError(stateView.message.toString())
                    showSnackBar(firebaseErrorMessage)
                    progressLoading.isVisible = false
                }
            }
        }
    }

    private fun setupProgressLoading() {
        val progressLoading = binding.forgotPasswordProgressLoading
        val progressLoadingImage = R.drawable.loading
        Glide.with(requireContext()).load(progressLoadingImage).into(progressLoading)
    }

}