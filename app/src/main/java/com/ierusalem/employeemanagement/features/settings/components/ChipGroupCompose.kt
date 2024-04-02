package com.ierusalem.employeemanagement.features.settings.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.features.settings.SettingsScreenEvents
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun ChipGroupCompose(
    intentReducer: (SettingsScreenEvents) -> Unit,
    locale: String
) {

    val chipList: List<String> = listOf(
        "English",
        "Russian",
        "Uzbek"
    )

    var selected by remember { mutableStateOf(locale) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp).padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        chipList.forEach { it ->
            Chip(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                    when(it){
                        "System" -> {intentReducer(SettingsScreenEvents.SystemLanClick)}
                        "English" -> {intentReducer(SettingsScreenEvents.EnglishLanClick)}
                        "Russian" -> {intentReducer(SettingsScreenEvents.RussianLanClick)}
                        "Uzbek" -> {intentReducer(SettingsScreenEvents.UzbLanClick)}
                    }
                }
            )
        }
    }

}

@Composable
fun Chip(
    title: String,
    selected: String,
    onSelected: (String) -> Unit
) {

    val isSelected = selected == title

    val background = if (isSelected) Color.Blue else Color.LightGray
    val contentColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .height(35.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(background)
            .clickable(
                onClick = {
                    onSelected(title)
                }
            )
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            AnimatedVisibility(visible = isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "check",
                    tint = Color.White
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 2.dp),
                text = title,
                color = contentColor,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleSmall
            )

        }
    }
}

@Preview
@Composable
fun ChipGroupComposePreview(){
    EmployeeManagementTheme {
        ChipGroupCompose(
            intentReducer = {},
            locale = "English"
        )
    }
}

@Preview
@Composable
fun ChipGroupComposePreviewDark(){
    EmployeeManagementTheme(darkTheme = true) {
        ChipGroupCompose(
            intentReducer = {},
            locale = ""
        )
    }
}