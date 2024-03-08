package com.ierusalem.employeemanagement.features.profile.presentation

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.ui.components.CommonJetHubLoginButton
import com.ierusalem.employeemanagement.ui.components.CommonJetHubLoginTextField
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun ProfileScreen() {
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

            CommonJetHubLoginTextField(
                value = "Username",
                label = "Username",
                onTextChanged = {
//                intentReducer(BasicAuthClickIntents.OnUsernameChanged(it))
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            CommonJetHubLoginTextField(
                value = "Password",
                label = "Password",
                onTextChanged = {
//                intentReducer(BasicAuthClickIntents.OnUsernameChanged(it))
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            CommonJetHubLoginButton(
                onClick = {
                    //intentReducer(LoginChooserClickIntents.BasicAuthentication)
                },
                text = "Login",
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
fun ProfileScreen_Preview_Light() {
    EmployeeManagementTheme(darkTheme = false) {
        ProfileScreen()
    }
}

@Preview
@Composable
fun ProfileScreen_Preview_Dark() {
    EmployeeManagementTheme(darkTheme = true) {
        ProfileScreen()
    }
}