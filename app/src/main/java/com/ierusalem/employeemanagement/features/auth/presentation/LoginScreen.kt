package com.ierusalem.employeemanagement.features.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.components.CommonJetHubLoginButton
import com.ierusalem.employeemanagement.ui.components.CommonTextFieldWithError
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun LoginScreen(
    state: LoginScreenState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.welcome_back),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = stringResource(id = R.string.sign_in_to_continue),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            CommonTextFieldWithError(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
                label = stringResource(id = R.string.username),
                errorMessage = state.usernameError?.asString(context),
                onTextChanged = {
                    onUsernameChanged(it)
                },
                value = state.username,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, // ** Done. Close the keyboard **
                    keyboardType = KeyboardType.Email
                )
            )

            CommonTextFieldWithError(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 16.dp),
                label = stringResource(id = R.string.password),
                errorMessage = state.passwordError?.asString(context),
                onTextChanged = {
                    onPasswordChanged(it)
                },
                value = state.password,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done, // ** Done. Close the keyboard **
                    keyboardType = KeyboardType.Password
                )
            )

            CommonJetHubLoginButton(
                onClick = onLoginClick,
                text = stringResource(id = R.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.primary),
            )
        }
    )
}

@Preview
@Composable
fun LoginScreen_Preview_Light() {
    EmployeeManagementTheme(darkTheme = false) {
        LoginScreen(
            state = LoginScreenState(),
            onUsernameChanged = {},
            onPasswordChanged = {},
            onLoginClick = {}
        )
    }
}

@Preview
@Composable
fun LoginScreen_Preview_Dark() {
    EmployeeManagementTheme(darkTheme = true) {
        LoginScreen(
            state = LoginScreenState(),
            onUsernameChanged = {},
            onPasswordChanged = {},
            onLoginClick = {}
        )
    }
}