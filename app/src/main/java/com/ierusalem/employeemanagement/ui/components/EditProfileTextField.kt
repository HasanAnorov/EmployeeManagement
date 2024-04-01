package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun SimpleFilledTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    leadingIcon: ImageVector? = null,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier,
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
        if (leadingIcon == null) {
            TextField(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
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
                placeholder = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
            )
        } else {
            TextField(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.titleSmall,
                value = value,
                keyboardOptions = keyboardType,
                leadingIcon = {
                    Icon(imageVector = leadingIcon, contentDescription = null)
                },
                onValueChange = { onValueChanged(it) },
                placeholder = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
            )
        }
    }
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