package com.ierusalem.employeemanagement.features.home.presentation.commands

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
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenState
import com.ierusalem.employeemanagement.ui.components.EmptyScreen
import com.ierusalem.employeemanagement.ui.components.WorkItem
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants

@Composable
fun CommandsScreen(
    modifier: Modifier = Modifier,
    status: String,
    intentReducer: (HomeScreenClickIntents) -> Unit,
    state: HomeScreenState
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
                        HomeScreenClickIntents.OnPullToRefreshCommands(
                            "yuborildi"
                        )
                    )

                    "qabulqildi" -> intentReducer(
                        HomeScreenClickIntents.OnPullToRefreshCommands(
                            "qabulqildi"
                        )
                    )

                    "bajarildi" -> intentReducer(
                        HomeScreenClickIntents.OnPullToRefreshCommands(
                            "bajarildi"
                        )
                    )

                    "bajarilmadi" -> intentReducer(
                        HomeScreenClickIntents.OnPullToRefreshCommands(
                            "bajarilmadi"
                        )
                    )

                    "kechikibbajarildi" -> intentReducer(
                        HomeScreenClickIntents.OnPullToRefreshCommands(
                            "kechikibbajarildi"
                        )
                    )
                    else -> intentReducer(HomeScreenClickIntents.OnPullToRefreshCommands("yuborildi"))
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
                            if (image.startsWith("/media")){
                                image =  "${Constants.BASE_URL}${command.userImage}"
                            }
                            WorkItem(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                image = image,
                                position = command.userUnvoni ?: stringResource(id = R.string.not_given),
                                fullName = "${command.user} ${command.userLastName}",
                                description = command.text,
                                deadline = command.endTime,
                                onItemClick = {
                                    intentReducer(HomeScreenClickIntents.OnItemClick(command.workId.toString()))
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
fun ComposeScreenPreview() {
    EmployeeManagementTheme {
        CommandsScreen(
            intentReducer = {},
            state = HomeScreenState(
                isDarkTheme = false
            ),
            status = ""
        )
    }
}

@Preview
@Composable
fun ComposeScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        CommandsScreen(
            intentReducer = {},
            state = HomeScreenState(
                isDarkTheme = true
            ),
            status = ""
        )
    }
}