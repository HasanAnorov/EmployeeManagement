package com.ierusalem.employeemanagement.features.home.presentation.employees

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.Result
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun EmployeeItem(
    modifier: Modifier = Modifier,
    employee: Result,
    intentReducer: (HomeScreenClickIntents) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier
                .weight(1F)
                .padding(start = 16.dp)
                .padding(vertical = 8.dp),
            text = employee.username + " " + employee.lastName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall
        )
        IconButton(onClick = { intentReducer(HomeScreenClickIntents.CreateCommand(employee.id)) }) {
            Icon(imageVector = Icons.Default.AddCircleOutline, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun EmployeeItemPreview() {
    EmployeeManagementTheme {
        EmployeeItem(
            intentReducer = {},
            employee = Result(
            email = "anorov@gmail.com",
                username = "Hasan",
                lastName = "Anorov",
                id = 1,
                image = "",
                phoneNo = "",
                unvoni = "",
                isStaff = false,
                xonasi = ""
            )
        )
    }
}

@Preview
@Composable
fun EmployeeItemPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        EmployeeItem(
            intentReducer = {},
            employee = Result(
                email = "anorov@gmail.com",
                username = "Hasan",
                lastName = "Anorov",
                id = 1,
                image = "",
                phoneNo = "",
                unvoni = "",
                isStaff = false,
                xonasi = ""
            )
        )
    }
}