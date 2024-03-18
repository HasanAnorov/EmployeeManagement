package com.ierusalem.employeemanagement.features.compose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun ComposeScreen(){

}

@Preview
@Composable
fun ComposeScreenPreview(){
    EmployeeManagementTheme {
        ComposeScreen()
    }
}

@Preview
@Composable
fun ComposeScreenPreviewDark(){
    EmployeeManagementTheme(darkTheme = true) {
        ComposeScreen()
    }
}