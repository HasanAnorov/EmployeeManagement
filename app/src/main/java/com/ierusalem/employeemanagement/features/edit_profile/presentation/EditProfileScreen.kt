package com.ierusalem.employeemanagement.features.edit_profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun EditProfileScreen(
    state: EditProfileScreenState,
    onPhotoPickerClicked: () -> Unit,
    onUsernameChanged: (String) -> Unit,
    onLastnameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPositionChanged: (String) -> Unit,
    onRoomChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
) {
    val verticalScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(verticalScrollState)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        content = {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(horizontal = 16.dp),
                    text = "Profile Edit Screen",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            EditProfileProperty(
                headline = "Enter new user name",
                value = state.newUsername,
                label = "New username here ...",
                onTextChanged = {
                    onUsernameChanged(it)
                }
            )
            EditProfileProperty(
                headline = "Enter new last name",
                value = state.newLastname,
                label = "New lastname here ...",
                onTextChanged = {
                    onLastnameChanged(it)
                }
            )
            EditProfileProperty(
                headline = "Enter new email",
                value = state.newEmail,
                label = "New email here ...",
                onTextChanged = {
                    onEmailChanged(it)
                }
            )
            EditProfileProperty(
                headline = "Enter new position ",
                value = state.newPosition,
                label = "New position here ...",
                onTextChanged = {
                    onPositionChanged(it)
                }
            )
            EditProfileProperty(
                headline = "Enter new room ",
                value = state.newRoom,
                label = "New room here ...",
                onTextChanged = {
                    onRoomChanged(it)
                }
            )
            EditProfileProperty(
                headline = "Enter new phone number ",
                value = state.newPhoneNumber,
                label = "New phone number here ...",
                onTextChanged = {
                    onPhoneNumberChanged(it)
                }
            )

            CommonJetHubLoginButton(
                onClick = onPhotoPickerClicked,
                text = if (state.imageUri != null) "${state.imageUri.path}" else "Choose Photo from Gallery",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.primary),
            )

            CommonJetHubLoginButton(
                onClick = {},
                text = "Save Profile",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.primary),
            )
        }
    )
}

@Composable
fun EditProfileProperty(
    headline: String,
    onTextChanged: (String) -> Unit,
    value: String,
    label: String
){
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        text = headline,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    CommonJetHubLoginTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
            .padding(horizontal = 16.dp),
        textStyle = MaterialTheme.typography.labelSmall,
        value = value,
        label = label,
        shape = RoundedCornerShape(12.dp),
        onTextChanged = {
                    onTextChanged(it)
        }
    )
}

@Preview
@Composable
fun EditProfileScreen_Preview_Light() {
    EmployeeManagementTheme(darkTheme = false) {
        EditProfileScreen(
            state = EditProfileScreenState(),
            onPhotoPickerClicked = {},
            onUsernameChanged = {},
            onLastnameChanged = {},
            onPhoneNumberChanged = {},
            onRoomChanged = {},
            onPositionChanged = {},
            onEmailChanged = {}
        )
    }
}

@Preview
@Composable
fun EditProfileScreen_Preview_Dark() {
    EmployeeManagementTheme(darkTheme = true) {
        EditProfileScreen(
            state = EditProfileScreenState(),
            onPhotoPickerClicked = {},
            onUsernameChanged = {},
            onLastnameChanged = {},
            onPhoneNumberChanged = {},
            onRoomChanged = {},
            onPositionChanged = {},
            onEmailChanged = {}
        )
    }
}