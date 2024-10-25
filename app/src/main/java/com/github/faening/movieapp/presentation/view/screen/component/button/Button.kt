package com.github.faening.movieapp.presentation.view.screen.component.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Button(
        onClick = onClick,
        modifier = modifier,
        properties = properties
    )
}

@Preview(showBackground = true)
@Composable
fun FilledButtonPreview() {
    FilledButton(
        onClick = {},
        properties = ButtonProperties(
            text = R.string.button_title,
        )
    )
}

@Composable
private fun Button(
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    properties: ButtonProperties
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = properties.backgroundColor,
            contentColor = properties.contentColor
        ),
        shape = properties.shape,
        modifier = modifier
            .height(48.dp),
    ) {
        properties.icon?.let { _ ->
            Icon(
                modifier = Modifier.
                width(24.dp)
                    .height(24.dp),
                painter = painterResource(properties.icon),
                contentDescription = properties.iconDescription?.let { description -> stringResource(description) },
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(stringResource(properties.text))
    }
}