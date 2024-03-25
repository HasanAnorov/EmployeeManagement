package com.ierusalem.employeemanagement.features.home.presentation.employees

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenState
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun EmployeesScreen(
    modifier: Modifier = Modifier,
    intentReducer: (HomeScreenClickIntents) -> Unit,
    state: HomeScreenState
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            content = {
                items(state.employees) { command ->
                    EmployeeItem(
                        employee = command,
                        intentReducer = intentReducer
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun EmployeesScreenPreview() {
    EmployeeManagementTheme {
        EmployeesScreen(
            intentReducer = {},
            state = HomeScreenState()
        )
    }
}

@Preview
@Composable
fun EmployeesScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        EmployeesScreen(
            intentReducer = {},
            state = HomeScreenState()
        )
    }
}