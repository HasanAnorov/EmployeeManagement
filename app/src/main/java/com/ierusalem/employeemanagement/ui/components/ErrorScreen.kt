package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String = stringResource(R.string.something_went_wrong),
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
fun ErrorScreen_LightPreview(){
    EmployeeManagementTheme(darkTheme = false) {
        ErrorScreen()
    }
}

@Preview
@Composable
fun ErrorScreen_DarkPreview(){
    EmployeeManagementTheme(darkTheme = true) {
        ErrorScreen()
    }
}