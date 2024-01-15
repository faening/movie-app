package com.github.faening.movieapp.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val view = activity?.currentFocus
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}

fun String.isEmailValid(): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return emailPattern.matches(this)
}

/*
* A senha deve conter:
*
* (?=.*[a-z]): Pelo menos uma letra minúscula.
* (?=.*[A-Z]): Pelo menos uma letra maiúscula.
* (?=.*\d): Pelo menos um número.
* (?=.*[@#$%^&+=!]): Pelo menos um caractere especial.
* .{6,}: Pelo menos 6 caracteres no total.
* */
fun String.isPasswordValid(): Boolean {
    val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{6,}\$\n")
    return passwordPattern.matches(this)
}