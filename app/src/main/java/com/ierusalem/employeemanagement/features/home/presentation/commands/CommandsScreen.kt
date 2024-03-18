package com.ierusalem.employeemanagement.features.home.presentation.commands

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.ui.components.FabButton
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun ComposeScreen(
    modifier: Modifier = Modifier,
    intentReducer: (HomeScreenClickIntents) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FabButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                // Offsets the FAB to compensate for CoordinatorLayout collapsing behaviour
                .offset(y = ((-16).dp), x = ((-16).dp)),
            onClick = { intentReducer(HomeScreenClickIntents.FabButtonClick) }
        )
    }
}

@Preview
@Composable
fun ComposeScreenPreview() {
    EmployeeManagementTheme {
        ComposeScreen(
            intentReducer = {}
        )
    }
}

@Preview
@Composable
fun ComposeScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        ComposeScreen(
            intentReducer = {}
        )
    }
}