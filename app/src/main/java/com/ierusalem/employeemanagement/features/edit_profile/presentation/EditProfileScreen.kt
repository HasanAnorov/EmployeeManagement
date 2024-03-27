package com.ierusalem.employeemanagement.features.edit_profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.components.CommonJetHubLoginButton
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.components.SimpleFilledTextField
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    state: EditProfileScreenState,
    onSaveClicked: () -> Unit,
    onPhotoPickerClicked: () -> Unit,
    onUsernameChanged: (String) -> Unit,
    onLastnameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPositionChanged: (String) -> Unit,
    onRoomChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onNavigationIconClicked: () ->  Unit,
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
            CommonTopBar(
                containerColor = MaterialTheme.colorScheme.background,
                titleColor = MaterialTheme.colorScheme.onBackground,
                onNavIconPressed = { onNavigationIconClicked() },
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                title = {
                    Text(
                        text = stringResource(id = R.string.edit_profile),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            )

            SimpleFilledTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
                value = state.newUsername,
                label = stringResource(id = R.string.username),
                onValueChanged = {
                    onUsernameChanged(it)
                }
            )
            SimpleFilledTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                value = state.newLastname,
                label = stringResource(R.string.lastname),
                onValueChanged = {
                    onLastnameChanged(it)
                }
            )
            SimpleFilledTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                value = state.newEmail,
                label = stringResource(R.string.email),
                onValueChanged = {
                    onEmailChanged(it)
                }
            )
            SimpleFilledTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                value = state.newPosition,
                label = stringResource(id = R.string.position),
                onValueChanged = {
                    onPositionChanged(it)
                }
            )
            SimpleFilledTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                value = state.newRoom,
                label = stringResource(id = R.string.room),
                onValueChanged = {
                    onRoomChanged(it)
                }
            )
            SimpleFilledTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                value = state.newPhoneNumber,
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = stringResource(id = R.string.phone_number),
                onValueChanged = {
                    onPhoneNumberChanged(it)
                }
            )

            CommonJetHubLoginButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                text = if (state.imageUri != null) stringResource(R.string.photo_picked) else stringResource(
                    R.string.local_storage
                ),
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                onClick = onPhotoPickerClicked,
            )

            CommonJetHubLoginButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                text = stringResource(id = R.string.save_profile),
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                onClick = onSaveClicked,
            )
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
            onEmailChanged = {},
            onSaveClicked = {},
            onNavigationIconClicked = {}
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
            onEmailChanged = {},
            onSaveClicked = {},
            onNavigationIconClicked = {}
        )
    }
}