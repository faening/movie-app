package com.github.faening.movieapp.presentation.auth.forgot_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.github.faening.movieapp.R
import com.github.faening.movieapp.databinding.FragmentForgotPasswordBinding
import com.github.faening.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val model: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        setupProgressLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeListeners() {
        buttonRetrieveAccountListener()
    }

    private fun buttonRetrieveAccountListener() {
        val buttonRetrieveAccount = binding.forgotPasswordButtonRetrieveAccount
        buttonRetrieveAccount.setOnClickListener {
            val email = binding.forgotPasswordEmail.text.toString().trim()
            if ( validateFormInputs(email) ) retrieveAccount(email)
        }
    }

    private fun validateFormInputs(email: String): Boolean {
        return email.isNotEmpty()
    }

    private fun retrieveAccount(email: String) {
        model.forgotPassword(email).observe(viewLifecycleOwner) { stateView ->
            val progressLoading = binding.forgotPasswordProgressLoading

            when(stateView) {
                is StateView.Loading -> {
                    progressLoading.isVisible = true
                }
                is StateView.Success -> {
                    Toast.makeText(requireContext(), "E-mail enviado com sucessso", Toast.LENGTH_SHORT).show()
                }
                is StateView.Error -> {
                    progressLoading.isVisible = false
                    Toast.makeText(requireContext(), stateView.message, Toast.LENGTH_SHORT).show()
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