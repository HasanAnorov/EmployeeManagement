package com.ierusalem.employeemanagement.features.statistics.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun StatisticsColumnModel(
    title: String,
    data: List<String>
) {
    Column(
        modifier = Modifier.padding(start = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text =title,
            modifier = Modifier.padding(vertical = 6.dp).padding(horizontal = 6.dp),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall
        )
        LazyColumn {
            items(data){
                Text(
                    text = it,
                    modifier = Modifier.padding(vertical = 6.dp).padding(horizontal = 6.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    EmployeeManagementTheme {
        Surface {
            StatisticsColumnModel(
                title = "Employees",
                data = listOf("Hasan", "Hasan","HasanHasan", "Hasan","Hasan", "Hasan","Hasan", "Hasan","Hasan", "Hasan","Hasan", "Hasan")
            )
        }
    }
}