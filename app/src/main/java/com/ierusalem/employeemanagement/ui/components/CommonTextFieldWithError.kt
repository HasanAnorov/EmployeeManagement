package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun CommonTextFieldWithError(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    errorMessage: String? = null,
    onTextChanged: (String) -> Unit,
) {
    Column(modifier= modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            textStyle = MaterialTheme.typography.titleMedium,
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                disabledLabelColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            onValueChange = {
                            onTextChanged(it)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next, // ** Done. Close the keyboard **
                keyboardType = KeyboardType.Text
            ),
            shape = RoundedCornerShape(size = 12.dp),
            singleLine = true,
        )
        errorMessage?.let{
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red.copy(0.8F)
            )
        }
    }
}

@Composable
@Preview
fun CommonTextFieldPreview(){
    EmployeeManagementTheme {
        CommonTextFieldWithError(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            label = "Username",
            errorMessage = "Login should be at least 3 characters",
            onTextChanged = {},
            value = ""
        )
    }
}

@Composable
@Preview
fun CommonTextFieldPreviewDark(){
    EmployeeManagementTheme {
        CommonTextFieldWithError(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            label = "Username",
            errorMessage = "Login should be at least 3 characters",
            onTextChanged = {},
            value = ""
        )
    }
}