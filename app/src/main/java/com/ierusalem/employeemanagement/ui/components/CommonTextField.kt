package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import androidx.compose.ui.text.TextStyle

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    value: String,
    label: String,
    shape: Shape = RoundedCornerShape(size = 12.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next, // ** Done. Close the keyboard **
        keyboardType = KeyboardType.Text
    )
) {
    TextField(
        modifier = modifier,
        value = value,
        textStyle = textStyle,
        colors = TextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            disabledLabelColor = Color.Red,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = label,
                style = textStyle,
                color = MaterialTheme.colorScheme.primary
            )
        },
        onValueChange = {
            onTextChanged(it)
        },
        keyboardOptions = keyboardOptions,
        shape = shape,
        singleLine = true,
    )
}

@Preview
@Composable
fun Preview_CommonTextField() {
    EmployeeManagementTheme {
        CommonTextField(
            value = "Username",
            label = "Username",
            onTextChanged = {},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}