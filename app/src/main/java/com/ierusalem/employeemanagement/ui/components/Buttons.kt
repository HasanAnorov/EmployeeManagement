package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun CommonJetHubLoginButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.clickable { onClick() },
        content = {
            Text(
                text = text,
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
                    .fillMaxWidth(),
                style = textStyle,
                color = textColor,
                textAlign = TextAlign.Center
            )
        },
    )
}

@Preview
@Composable
private fun CommonJetHubLoginButton_LightPreview() {
    EmployeeManagementTheme {
        CommonJetHubLoginButton(
            text = stringResource(id = R.string.login),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.primary),
        )
    }
}

@Preview
@Composable
private fun CommonJetHubLoginButton_DarkPreview() {
    EmployeeManagementTheme(darkTheme = true) {
        CommonJetHubLoginButton(
            text = stringResource(id = R.string.login),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.primary),
        )
    }
}

