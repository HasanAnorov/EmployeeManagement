package com.ierusalem.employeemanagement.features.compose.presentation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("stringResource(R.string.dismiss")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.dismiss))
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    EmployeeManagementTheme {
        AlertDialogExample(
            onDismissRequest = {  },
            onConfirmation = {  },
            dialogTitle = "Storage",
            dialogText = "Give access to use data!",
        )
    }
}