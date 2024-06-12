package com.ierusalem.employeemanagement.features.staff_home.presentation.commands_screen

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
import com.ierusalem.employeemanagement.features.staff_home.domain.StaffHomeScreenState
import com.ierusalem.employeemanagement.features.staff_home.presentation.StaffHomeScreenEvents
import com.ierusalem.employeemanagement.ui.components.EmptyScreen
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import com.ierusalem.employeemanagement.ui.components.WorkItem
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.core.utils.Constants
import com.ierusalem.employeemanagement.core.utils.Resource

@Composable
fun StaffComposeScreen(
    modifier: Modifier = Modifier,
    status: String,
    intentReducer: (StaffHomeScreenEvents) -> Unit,
    state: StaffHomeScreenState
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        @Suppress("DEPRECATION")
        SwipeRefresh(
            modifier = Modifier.fillMaxSize(),
            state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
            onRefresh = {
                when (status) {
                    "kechikibbajarildi" -> intentReducer(
                        StaffHomeScreenEvents.OnPullToRefreshCommands(
                            "kechikibbajarildi"
                        )
                    )
                    "yuborildi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("yuborildi"))
                    "qabulqildi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("qabulqildi"))
                    "bajarildi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("bajarildi"))
                    "bajarilmadi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("bajarilmadi"))
                    else -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("yuborildi"))
                }
            }
        ) {
            val data = when (status) {
                "yuborildi" -> state.commandsSent
                "qabulqildi" -> state.commandsReceived
                "bajarildi" -> state.commandsDone
                "bajarilmadi" -> state.commandsNotDone
                "kechikibbajarildi" -> state.commandsLateDone
                else -> state.commandsReceived
            }
            when (data) {
                is Resource.Loading -> LoadingScreen()
                is Resource.Success -> {
                    if (data.data.isNullOrEmpty()) {
                        EmptyScreen(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxSize(),
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .padding(bottom = 8.dp, top = 16.dp)
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            content = {
                                itemsIndexed(data.data) { index, command ->
                                    var image = command.adminImage
                                    if (image.startsWith("/media")) {
                                        image = "${Constants.BASE_URL}${command.adminImage}"
                                    }
                                    WorkItem(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .padding(top = 8.dp),
                                        fullName = "${command.adminUsername} ${command.adminLastname}",
                                        image = image,
                                        position = command.adminPosition?: stringResource(id = R.string.not_given),
                                        description = command.text,
                                        deadline = command.endTime,
                                        onItemClick = {
                                            intentReducer(StaffHomeScreenEvents.OnItemClick(command.workId.toString()))
                                        }
                                    )
                                    if (index < data.data.size - 1) {
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

                is Resource.Failure -> ErrorScreen()
            }
        }
    }
}

@Preview
@Composable
fun ComposeScreenPreview() {
    EmployeeManagementTheme {
        StaffComposeScreen(
            intentReducer = {},
            state = StaffHomeScreenState(false),
            status = ""
        )
    }
}

@Preview
@Composable
fun ComposeScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        StaffComposeScreen(
            intentReducer = {},
            state = StaffHomeScreenState(true),
            status = ""
        )
    }
}