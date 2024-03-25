package com.ierusalem.employeemanagement.features.staff_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun StaffHomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Staff Screen")
    }
}

@Preview
@Composable
fun StaffHomeScreenPreview(){
    EmployeeManagementTheme {
        StaffHomeScreen()
    }
}

@Preview
@Composable
fun StaffHomeScreenPreviewDark(){
    EmployeeManagementTheme(darkTheme = true) {
        StaffHomeScreen()
    }
}