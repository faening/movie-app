package com.github.faening.movieapp.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.github.faening.movieapp.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.initializeToolbar(toolbar: Toolbar, showIconNavigation: Boolean = true) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""

    if (showIconNavigation) {
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    toolbar.setNavigationOnClickListener {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}

fun Fragment.hideKeyboard() {
    val view = this.view
    view?.let {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}

fun Fragment.showSnackBar(message: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    view?.let {
        Snackbar.make(it, message, duration).show()
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
    val passwordPattern = Regex("^(?=.*[a-zA-Z\\d@#\$%^&+=!]).{6,}\$")
    return passwordPattern.matches(this)
}