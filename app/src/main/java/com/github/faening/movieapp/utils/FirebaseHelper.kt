package com.github.faening.movieapp.utils

import com.github.faening.movieapp.R
import com.google.firebase.auth.FirebaseAuth

class FirebaseHelper {
    companion object {
        fun getAuth() = FirebaseAuth.getInstance()

        fun isAuthenticated() = getAuth().currentUser != null

        fun getUserId() = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        fun checkError(error: String): Int {
            return when {
                error.contains("There is no user record corresponding to this identifier") -> {
                    R.string.firebase_auth_error_account_not_registered
                }
                error.contains("The email address is badly formatted") -> {
                    R.string.firebase_auth_error_invalid_email
                }
                error.contains("The password is invalid") -> {
                    R.string.firebase_auth_error_invalid_password
                }
                error.contains("The email address is already in use by another account") -> {
                    R.string.firebase_auth_error_email_in_use
                }
                error.contains("Password should be at least 6 characters") -> {
                    R.string.firebase_auth_error_strong_password
                }
                else -> {
                    R.string.text_validation_generic_error
                }
            }
        }
    }
}