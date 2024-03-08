package com.ierusalem.employeemanagement.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun HomeScreen(){

}

@Preview
@Composable
fun Preview_HomeScreen_Light(){
    EmployeeManagementTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview
@Composable
fun Preview_HomeScreen_Dark(){
    EmployeeManagementTheme(darkTheme = true) {
        HomeScreen()
    }
}