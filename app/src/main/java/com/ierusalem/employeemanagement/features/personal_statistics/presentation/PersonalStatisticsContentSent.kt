package com.ierusalem.employeemanagement.features.personal_statistics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.personal_statistics.domain.PersonalStatisticsState
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun PersonalStatisticsContentSent(
    intentReducer: (PersonalStatisticsEvents) -> Unit,
    state: PersonalStatisticsState
) {
    val stateEmployees =  rememberLazyListState()
    val stateSent = rememberLazyListState()
    val stateDone = rememberLazyListState()
    val stateNotDone = rememberLazyListState()
    val stateLateDone = rememberLazyListState()
    val received = rememberLazyListState()
    LaunchedEffect(stateEmployees.firstVisibleItemScrollOffset) {
        stateSent.scrollToItem(
            stateEmployees.firstVisibleItemIndex,
            stateEmployees.firstVisibleItemScrollOffset
        )
        stateDone.scrollToItem(
            stateEmployees.firstVisibleItemIndex,
            stateEmployees.firstVisibleItemScrollOffset
        )
        stateNotDone.scrollToItem(
            stateEmployees.firstVisibleItemIndex,
            stateEmployees.firstVisibleItemScrollOffset
        )
        stateLateDone.scrollToItem(
            stateEmployees.firstVisibleItemIndex,
            stateEmployees.firstVisibleItemScrollOffset
        )
        received.scrollToItem(
            stateEmployees.firstVisibleItemIndex,
            stateEmployees.firstVisibleItemScrollOffset
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(
                    modifier = Modifier
                        .weight(1F)
                        .padding(top = 6.dp)
                        .padding(horizontal = 6.dp),
                    text = stringResource(R.string.personal_sent_statistics),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                IconButton(
                    onClick = { intentReducer(PersonalStatisticsEvents.DownloadPersonalStatisticsSent) },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.download),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        )
        val soloHorizontalScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .horizontalScroll(soloHorizontalScrollState)
                .padding(start = 8.dp),
            content = {
                Column(
                    modifier = Modifier.padding(start = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            text = stringResource(id = R.string.commands_sent),
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = state.sentSoloSent,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                )
                Column(
                    modifier = Modifier.padding(start = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            text = stringResource(id = R.string.commands_received),
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = state.sentSoloReceived,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                )
                Column(
                    modifier = Modifier.padding(start = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            text = stringResource(id = R.string.commands_done),
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = state.sentSoloDone,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                )
                Column(
                    modifier = Modifier.padding(start = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            text = stringResource(id = R.string.late_done),
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = state.sentSoloLateDone,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                )
                Column(
                    modifier = Modifier.padding(start = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            text = stringResource(id = R.string.commands_not_done),
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = state.sentSoloNotDone,
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 6.dp),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.9F),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                )
            }
        )
        HorizontalDivider()
        val horizontalScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .weight(1F)
                .horizontalScroll(horizontalScrollState)
                .padding(start = 8.dp, bottom = 8.dp),
            content = {
                Column(
                    modifier = Modifier.padding(start = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.personal_info),
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .padding(horizontal = 6.dp),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    LazyColumn (state = stateEmployees){
                        items(state.sentEmployees) {
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
                        text = stringResource(id = R.string.commands_sent),
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .padding(horizontal = 6.dp),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    LazyColumn(state = stateSent, userScrollEnabled = false) {
                        items(state.sentSent) {
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
                        text = stringResource(id = R.string.commands_received),
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .padding(horizontal = 6.dp),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    LazyColumn(state = received, userScrollEnabled = false) {
                        items(state.sentReceived) {
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
                        text = stringResource(id = R.string.commands_done),
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .padding(horizontal = 6.dp),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    LazyColumn(state = stateDone, userScrollEnabled = false) {
                        items(state.sentDone) {
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
                        text = stringResource(id = R.string.late_done),
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .padding(horizontal = 6.dp),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    LazyColumn(state = stateLateDone, userScrollEnabled = false) {
                        items(state.sentLateDone) {
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
                        text = stringResource(id = R.string.commands_not_done),
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .padding(horizontal = 6.dp),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    LazyColumn(state = stateNotDone, userScrollEnabled = false) {
                        items(state.sentNotDone) {
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
        )
    }
}

@Preview
@Composable
private fun Preview() {
    EmployeeManagementTheme {
        PersonalStatisticsContent(
            intentReducer = {},
            state = PersonalStatisticsState()
        )
    }
}