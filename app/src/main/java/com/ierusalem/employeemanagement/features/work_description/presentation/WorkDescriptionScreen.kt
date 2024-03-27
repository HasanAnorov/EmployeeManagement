package com.ierusalem.employeemanagement.features.work_description.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionScreenState
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun WorkDescriptionScreen(state: WorkDescriptionScreenState) {

}

@Preview
@Composable
private fun WorkDescriptionScreenPreview() {
    EmployeeManagementTheme {
        WorkDescriptionScreen(
            state = WorkDescriptionScreenState()
        )
    }
}

@Preview
@Composable
private fun WorkDescriptionScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        WorkDescriptionScreen(
            state = WorkDescriptionScreenState()
        )
    }
}