package com.ierusalem.employeemanagement.features.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.features.settings.SettingsScreenEvents
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun ThemeChipGroupCompose(intentReducer: (SettingsScreenEvents) -> Unit) {

    val chipList: List<String> = listOf(
        "System",
        "Light",
        "Dark"
    )

    var selected by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        chipList.forEach { it ->
            Chip(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                    when(it){
                        "System" -> {intentReducer(SettingsScreenEvents.SystemThemeClick)}
                        "Light" -> {intentReducer(SettingsScreenEvents.LightThemeClick)}
                        "Dark" -> {intentReducer(SettingsScreenEvents.DarkThemeClick)}
                    }
                }
            )
        }
    }

}

@Preview
@Composable
fun ThemeChipGroupComposePreview(){
    EmployeeManagementTheme {
        ThemeChipGroupCompose(
            intentReducer = {}
        )
    }
}

@Preview
@Composable
fun ThemeChipGroupComposePreviewDark(){
    EmployeeManagementTheme(darkTheme = true) {
        ThemeChipGroupCompose(
            intentReducer = {}
        )
    }
}