package com.github.faening.movieapp.presentation.view.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.faening.movieapp.R

@Composable
fun FilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: ButtonProperties
) {
    ButtonFactory(
        onClick = onClick,
        modifier = modifier,
        buttonType = ButtonType.FILLED,
        properties = properties
    )
}

@Preview(showBackground = true)
@Composable
private fun FilledButtonPreview() {
    FilledButton(onClick = {}, properties = ButtonProperties(R.string.button_title))
}

@Composable
fun TonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: ButtonProperties
) {
    ButtonFactory(
        onClick = onClick,
        modifier = modifier,
        buttonType = ButtonType.TONAL,
        properties = properties
    )
}

@Preview(showBackground = true)
@Composable
private fun TonalButtonPreview() {
    TonalButton(onClick = {}, properties = ButtonProperties(R.string.button_title))
}

@Composable
fun OutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: ButtonProperties
) {
    ButtonFactory(
        onClick = onClick,
        modifier = modifier,
        buttonType = ButtonType.OUTLINE,
        properties = properties
    )
}

@Preview(showBackground = false)
@Composable
private fun OutlinedButtonPreview() {
    OutlineButton(onClick = {}, properties = ButtonProperties(R.string.button_title))
}

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: ButtonProperties
) {
    ButtonFactory(
        onClick = onClick,
        modifier = modifier,
        buttonType = ButtonType.TEXT,
        properties = properties
    )
}

@Preview(showBackground = false)
@Composable
private fun TextButtonPreview() {
    TextButton(onClick = {}, properties = ButtonProperties(R.string.button_title))
}

private enum class ButtonType {
    FILLED,
    TONAL,
    OUTLINE,
    TEXT
}

@Composable
private fun ButtonFactory(
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    buttonType: ButtonType,
    properties: ButtonProperties
) {
    val colors = when(buttonType) {
        ButtonType.FILLED -> ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.red),
            contentColor = colorResource(R.color.white)
        )
        ButtonType.TONAL -> ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.red_700),
            contentColor = colorResource(R.color.white)
        )
        ButtonType.OUTLINE -> ButtonDefaults.outlinedButtonColors(
            containerColor = colorResource(R.color.white),
            contentColor = colorResource(R.color.black_700)
        )
        ButtonType.TEXT -> ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.white),
            contentColor = colorResource(R.color.red)
        )
    }

    val iconColor = if (buttonType == ButtonType.OUTLINE) {
        colorResource(R.color.black_700)
    } else {
        Color.Unspecified
    }

    val border = if (buttonType == ButtonType.OUTLINE) {
        BorderStroke(1.dp, colorResource(R.color.black_500))
    } else {
        null
    }

    Button(
        onClick = onClick,
        colors = colors,
        border = border,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier.height(52.dp)
    ) {
        properties.icon?.let {
            Icon(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp),
                painter = painterResource(it),
                contentDescription = properties.iconDescription?.let { description -> stringResource(description) },
                tint = iconColor
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(stringResource(properties.text))
    }
}