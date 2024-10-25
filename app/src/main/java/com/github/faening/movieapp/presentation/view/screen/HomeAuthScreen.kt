package com.github.faening.movieapp.presentation.view.screen

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.faening.movieapp.R
import com.github.faening.movieapp.presentation.view.screen.component.button.ButtonProperties
import com.github.faening.movieapp.presentation.view.screen.component.button.FilledButton

@Composable
// @HiltViewModel
fun HomeAuthScreen(
    navController: NavController,
    resources: Resources = LocalContext.current.resources
) {
    val paddingMedium = 16.dp     // resources.getDimension(R.dimen.padding_medium).dp
    val paddingExtraLarge = 32.dp // resources.getDimension(R.dimen.padding_extra_large).dp

    val coverImage = painterResource(R.drawable.img_home_auth)
    val coverImageDescription = stringResource(R.string.home_auth_fragment_content_description_cover)
    val coverImageHeight = 200.dp

    val presentationText = stringResource(R.string.home_auth_fragment_title)

    val buttonFacebookTitle = R.string.home_auth_fragment_text_button_continue_with_facebook
    val buttonFacebookIcon = R.drawable.ic_facebook_colorized
    val buttonFacebookIconDescription = R.string.home_auth_fragment_text_button_continue_with_facebook

    val buttonGoogleTitle = R.string.home_auth_fragment_text_button_continue_with_google
    val buttonGoogleIcon = R.drawable.ic_google_colorized
    val buttonGoogleIconDescription = R.string.home_auth_fragment_text_button_continue_with_google

    val dividerText = stringResource(R.string.home_auth_fragment_text_social_or_password)

    val buttonSignInTitle = stringResource(R.string.home_auth_fragment_text_button_sign_in_with_password)

    val textDontHaveAccount = stringResource(R.string.home_auth_fragment_text_dont_have_an_account)
    val linkDontHaveAccount = stringResource(R.string.home_auth_fragment_text_sign_up)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = coverImage,
            contentDescription = coverImageDescription,
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(coverImageHeight)
        )
        Spacer(modifier =Modifier.height(paddingExtraLarge))
        Text(text = presentationText)
        Spacer(modifier =Modifier.height(paddingExtraLarge))
        FilledButton(
            onClick = { navigateToSignIn(navController) },
            properties = ButtonProperties(
                text = buttonFacebookTitle,
                icon = buttonFacebookIcon,
                iconDescription = buttonFacebookIconDescription
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(paddingMedium))
        FilledButton(
            onClick = { navigateToSignIn(navController) },
            properties = ButtonProperties(
                text = buttonGoogleTitle,
                icon = buttonGoogleIcon,
                iconDescription = buttonGoogleIconDescription
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(paddingExtraLarge))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = dividerText)
        }
        Spacer(modifier = Modifier.height(paddingExtraLarge))
        Button(
            onClick = { navigateToSignIn(navController) },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text(text = buttonSignInTitle)
        }
        Spacer(modifier = Modifier.height(paddingExtraLarge))
        Row {
            Text(text = textDontHaveAccount)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = linkDontHaveAccount,
                modifier = Modifier.clickable { navigateToSignIn(navController) }
            )
        }
    }
}

fun navigateToSignIn(navController: NavController) {
    navController.navigate(R.id.action_homeAuthFragment_to_signInFragment)
}

@Preview(showBackground = true)
@Composable
fun HomeAuthScreenPreview() {
    HomeAuthScreen(navController = rememberNavController())
}