package com.ierusalem.employeemanagement.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Card
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
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun FileItemUrl(
    modifier: Modifier = Modifier,
    file: String,
    onDownloadFile:() -> Unit,
) {
    val url = Uri.parse(file).lastPathSegment ?: "Unknown file"
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
                    .weight(1F)
                    .padding(start = 16.dp)
                    .padding(vertical = 8.dp),
                text = url,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(onClick = onDownloadFile) {
                Icon(imageVector = Icons.Default.Download, contentDescription = null)
            }
        }

    }
}

@Preview
@Composable
fun FileItemPreview() {
    EmployeeManagementTheme {
        FileItemUrl(
            file = "Example file",
            onDownloadFile = {}
        )
    }
}

@Preview
@Composable
fun FileItemPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        FileItemUrl(
            file = "Example file",
            onDownloadFile = {}
        )
    }
}

