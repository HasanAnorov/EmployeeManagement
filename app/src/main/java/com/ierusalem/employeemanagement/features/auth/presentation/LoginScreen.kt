package com.ierusalem.employeemanagement.features.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Welcome \nBack",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = "Sign in to continue",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            CommonTextFieldWithError(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
                label = "Username",
                errorMessage = state.usernameError,
                onTextChanged = {
                    onUsernameChanged(it)
                },
                value = state.username
            )

            CommonTextFieldWithError(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 16.dp),
                label = "Password",
                errorMessage = state.passwordError,
                onTextChanged = {
                    onPasswordChanged(it)
                },
                value = state.password
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