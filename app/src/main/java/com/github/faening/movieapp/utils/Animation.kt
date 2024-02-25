package com.github.faening.movieapp.utils

import android.animation.ObjectAnimator
import android.view.View

fun fadeIn(view: View, initialValue: Float = 0f, finalValue: Float = 1f, duration: Long = 1000) {
    val fadeIn = ObjectAnimator.ofFloat(view, "alpha",  initialValue, finalValue)
    fadeIn.duration = duration
    fadeIn.start()
}

fun fadeOut(view: View, initialValue: Float = 1f, finalValue: Float = 0f, duration: Long = 1000) {
    val fadeOut = ObjectAnimator.ofFloat(view, "alpha",  initialValue, finalValue)
    fadeOut.duration = duration
    fadeOut.start()
}