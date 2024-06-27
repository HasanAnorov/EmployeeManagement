package com.ierusalem.employeemanagement.features.private_jobs.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.private_jobs.domain.PrivateJobsState
import com.ierusalem.employeemanagement.ui.components.EmptyScreen
import com.ierusalem.employeemanagement.ui.components.WorkItem
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.core.utils.Constants

@Composable
fun PrivateCommandsScreen(
    modifier: Modifier = Modifier,
    status: String,
    intentReducer: (PrivateJobsEvents) -> Unit,
    state: PrivateJobsState
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        @Suppress("DEPRECATION")
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
            onRefresh = {
                when (status) {
                    "yuborildi" -> intentReducer(
                        PrivateJobsEvents.OnPullToRefreshCommands(
                            "yuborildi"
                        )
                    )

                    "qabulqildi" -> intentReducer(
                        PrivateJobsEvents.OnPullToRefreshCommands(
                            "qabulqildi"
                        )
                    )

                    "bajarildi" -> intentReducer(
                        PrivateJobsEvents.OnPullToRefreshCommands(
                            "bajarildi"
                        )
                    )

                    "bajarilmadi" -> intentReducer(
                        PrivateJobsEvents.OnPullToRefreshCommands(
                            "bajarilmadi"
                        )
                    )

                    "kechikibbajarildi" -> intentReducer(
                        PrivateJobsEvents.OnPullToRefreshCommands(
                            "kechikibbajarildi"
                        )
                    )

                    else -> intentReducer(PrivateJobsEvents.OnPullToRefreshCommands("yuborildi"))
                }
            }
        ) {
            val data = when (status) {
                "yuborildi" -> state.commandsSent
                "qabulqildi" -> state.commandsReceived
                "bajarildi" -> state.commandsDone
                "bajarilmadi" -> state.commandsNotDone
                "kechikibbajarildi" -> state.commandsLateDone
                else -> state.commandsSent
            }
            if (data.isEmpty()) {
                EmptyScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 8.dp, top = 8.dp)
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    content = {
                        itemsIndexed(data) { index, command ->
                            var image = command.adminImage
                            if (image.startsWith("/media")) {
                                image = "${Constants.BASE_URL}${command.adminImage}"
                            }
                            WorkItem(
                                modifier = Modifier,
                                image = image,
                                position = command.adminPosition
                                    ?: stringResource(id = R.string.not_given),
                                fullName = "${command.adminUsername} ${command.adminLastname}",
                                description = command.text,
                                deadline = command.endTime,
                                onItemClick = {
                                    intentReducer(PrivateJobsEvents.OnItemClick(command.workId.toString(), status))
                                }
                            )
                            if (index < data.size - 1) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 16.dp,
                                            vertical = 16.dp
                                        )
                                        .clip(RoundedCornerShape(1.dp))
                                        .background(MaterialTheme.colorScheme.outline)
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PrivateCommandsScreenPreview() {
    EmployeeManagementTheme {
        PrivateCommandsScreen(
            intentReducer = {},
            state = PrivateJobsState(),
            status = ""
        )
    }
}

@Preview
@Composable
fun PrivateCommandsScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        PrivateCommandsScreen(
            intentReducer = {},
            state = PrivateJobsState(),
            status = ""
        )
    }
}