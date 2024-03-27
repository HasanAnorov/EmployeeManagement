package com.ierusalem.employeemanagement.features.staff_home.presentation.commands_screen

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
import com.ierusalem.employeemanagement.features.staff_home.domain.StaffHomeScreenState
import com.ierusalem.employeemanagement.features.staff_home.presentation.StaffHomeScreenEvents
import com.ierusalem.employeemanagement.ui.components.WorkItem
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Resource

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
            state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
            onRefresh = {
                when(status){
                    "yuborildi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("yuborildi"))
                    "qabulqildi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("qabulqildi"))
                    "bajarildi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("bajarildi"))
                    "bajarilmadi" -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("bajarilmadi"))
                    else -> intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands("yuborildi"))
                }
            }
        ) {
            val data = when(status){
                "yuborildi" -> state.commandsSent
                "qabulqildi" -> state.commandsReceived
                "bajarildi" -> state.commandsDone
                "bajarilmadi" -> state.commandsNotDone
                else -> state.commandsReceived
            }
            when(data){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        content = {
                            item(){

                            }
                            items(data.data!!) { command ->
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
                is Resource.Failure -> {}
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
            state = StaffHomeScreenState(),
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
            state = StaffHomeScreenState(),
            status = ""
        )
    }
}