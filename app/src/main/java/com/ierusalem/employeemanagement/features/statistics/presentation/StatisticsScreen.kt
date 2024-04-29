package com.ierusalem.employeemanagement.features.statistics.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.statistics.domain.StatisticsUiState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(
    state: StatisticsUiState,
    intentReducer: (StatisticsScreenEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CommonTopBar(
            containerColor = MaterialTheme.colorScheme.background,
            onNavIconPressed = {
                intentReducer(StatisticsScreenEvents.NavIconClick)
            },
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            title = {
                Text(
                    text = stringResource(id = R.string.statistics),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            },
            actions = {
                IconButton(
                    onClick = { intentReducer(StatisticsScreenEvents.DownloadStatistics) },
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.download),
                            contentDescription = null
                        )
                    }
                )
            }
        )
        HorizontalDivider()
        val horizontalScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .horizontalScroll(horizontalScrollState)
                .padding(start = 16.dp),
        ) {
            Column(
                modifier = Modifier.padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text ="Employees",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 6.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                LazyColumn {
                    items(state.employees){
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text ="Sent",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 6.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                LazyColumn {
                    items(state.sent){
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text ="Received",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 6.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                LazyColumn {
                    items(state.received){
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text ="Done",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 6.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                LazyColumn {
                    items(state.done){
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text ="Late Done",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 6.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                LazyColumn {
                    items(state.lateDone){
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text ="Not done",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 6.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                LazyColumn {
                    items(state.notDone){
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_Statistics_Light() {
    EmployeeManagementTheme(darkTheme = false) {
        StatisticsScreen(
            state = StatisticsUiState(),
            intentReducer = {}
        )
    }
}