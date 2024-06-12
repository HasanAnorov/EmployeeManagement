package com.ierusalem.employeemanagement.features.create_work.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import java.io.File

@Composable
fun FileItem(
    modifier: Modifier = Modifier,
    file: File
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp).padding(vertical = 8.dp),
                text = file.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )
        }

    }
}

@Preview
@Composable
fun FileItemPreview() {
    EmployeeManagementTheme {
        FileItem(
            file = File.createTempFile("name","suffix")
        )
    }
}

@Preview
@Composable
fun FileItemPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        FileItem(
            file = File.createTempFile("name","suffix")
        )
    }
}

