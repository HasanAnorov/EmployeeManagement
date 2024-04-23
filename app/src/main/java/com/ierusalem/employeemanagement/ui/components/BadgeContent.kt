package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun Badge(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(6.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary else Color.White
            )
            .clickable {
                onClick()
            },
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "Tab",
                    fontSize = 16.sp,
                    color = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .size(24.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(0.5F)),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = "12",
                            fontSize = 16.sp,
                            color = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun Light() {
    EmployeeManagementTheme {
        Badge(isSelected = false, onClick = {})
    }
}