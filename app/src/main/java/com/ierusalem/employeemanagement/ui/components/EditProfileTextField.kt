package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun SimpleFilledTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                shape = RoundedCornerShape(12.dp)
            ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        textStyle = MaterialTheme.typography.titleSmall,
        value = value,
        keyboardOptions = keyboardType,
        onValueChange = { onValueChanged(it) },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall
            )
        },
    )
}

@Preview
@Composable
fun SimpleFilledTextFieldPreview() {
    EmployeeManagementTheme {
        SimpleFilledTextField(
            value = "Hasan",
            label = "Username",
            onValueChanged = {}
        )
    }
}

@Preview
@Composable
fun SimpleFilledTextFieldPreviewDark() {
    EmployeeManagementTheme {
        SimpleFilledTextField(
            value = "Hasan",
            label = "Username",
            onValueChanged = {}
        )
    }
}