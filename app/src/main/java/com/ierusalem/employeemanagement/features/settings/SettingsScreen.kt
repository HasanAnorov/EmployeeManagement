package com.ierusalem.employeemanagement.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.settings.components.ChipGroupCompose
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    intentReducer: (SettingsScreenEvents) -> Unit,
    currentLocale: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CommonTopBar(
            containerColor = MaterialTheme.colorScheme.background,
            onNavIconPressed = { intentReducer(SettingsScreenEvents.NavIconClicked) },
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            title = {
                Text(
                    text = stringResource(id = R.string.profile),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        )
        Text(
            text = stringResource(R.string.language),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ChipGroupCompose(
            intentReducer = { event -> intentReducer(event) },
            locale = currentLocale
        )
//        Text(
//            text = stringResource(R.string.app_theme),
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//        ThemeChipGroupCompose(
//            intentReducer = {event ->intentReducer(event)}
//        )
    }
}

@Preview
@Composable
fun SettingPreview() {
    EmployeeManagementTheme {
        SettingsScreen(
            intentReducer = {},
            currentLocale = ""
        )
    }
}

@Preview
@Composable
fun SettingPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        SettingsScreen(
            intentReducer = {},
            currentLocale = ""
        )
    }
}