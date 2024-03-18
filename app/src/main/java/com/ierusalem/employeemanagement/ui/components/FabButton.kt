package com.ierusalem.employeemanagement.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun FabButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Preview
@Composable
fun FabButtonLightPreview() {
    EmployeeManagementTheme {
        FabButton {

        }
    }
}

@Preview
@Composable
fun FabButtonDarkPreview() {
    EmployeeManagementTheme {
        FabButton {

        }
    }
}