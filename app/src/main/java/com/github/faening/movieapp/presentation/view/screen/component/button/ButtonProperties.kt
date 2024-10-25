package com.github.faening.movieapp.presentation.view.screen.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ButtonProperties(
    val backgroundColor: Color = Color.Red,
    val contentColor: Color = Color.White,
    val shape: RoundedCornerShape = RoundedCornerShape(24.dp),

    // Text Properties
    val text: Int,

    // Icon Properties
    val icon: Int? = null,
    val iconDescription: Int? = null
)