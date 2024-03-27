package com.ierusalem.employeemanagement.features.home.presentation.commands

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenState
import com.ierusalem.employeemanagement.ui.components.WorkItem
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun ComposeScreen(
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
                when(status){
                    "yuborildi" -> intentReducer(HomeScreenClickIntents.OnPullToRefreshCommands("yuborildi"))
                    "qabulqildi" -> intentReducer(HomeScreenClickIntents.OnPullToRefreshCommands("qabulqildi"))
                    "bajarildi" -> intentReducer(HomeScreenClickIntents.OnPullToRefreshCommands("bajarildi"))
                    "bajarilmadi" -> intentReducer(HomeScreenClickIntents.OnPullToRefreshCommands("bajarilmadi"))
                    else -> intentReducer(HomeScreenClickIntents.OnPullToRefreshCommands("yuborildi"))
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                content = {
                    val data = when(status){
                        "yuborildi" -> state.commandsSent
                        "qabulqildi" -> state.commandsReceived
                        "bajarildi" -> state.commandsDone
                        "bajarilmadi" -> state.commandsNotDone
                        else -> state.commandsSent
                    }
                    items(data) { command ->
                        WorkItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 8.dp),
                            title = command.text,
                            from = command.user,
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ComposeScreenPreview() {
    EmployeeManagementTheme {
        ComposeScreen(
            intentReducer = {},
            state = HomeScreenState(),
            status = ""
        )
    }
}

@Preview
@Composable
fun ComposeScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        ComposeScreen(
            intentReducer = {},
            state = HomeScreenState(),
            status = ""
        )
    }
}