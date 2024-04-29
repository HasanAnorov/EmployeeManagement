@file:Suppress("DEPRECATION")

package com.ierusalem.employeemanagement.features.home.presentation.employees

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenState
import com.ierusalem.employeemanagement.ui.components.EmptyScreen
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun EmployeesScreen(
    modifier: Modifier = Modifier,
    intentReducer: (HomeScreenClickIntents) -> Unit,
    state: HomeScreenState
) {
    val results = state.employees.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TextField(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
                .fillMaxWidth(),
            value = state.searchText,
            textStyle = MaterialTheme.typography.titleMedium,
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                disabledLabelColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.outline.copy(0.2F),
                focusedContainerColor = MaterialTheme.colorScheme.outline.copy(0.2F)
            ),
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            onValueChange = {
                intentReducer(HomeScreenClickIntents.SearchTextChanged(it))
            },
            shape = RoundedCornerShape(size = 12.dp),
            singleLine = true,
        )
        SwipeRefresh(
            modifier = Modifier.weight(1F),
            state = rememberSwipeRefreshState(isRefreshing = state.isEmployeesLoading),
            onRefresh = {
                intentReducer(
                    HomeScreenClickIntents.OnPullToRefreshEmployees
                )
            }
        ) {
            when (results.loadState.refresh) {
                is LoadState.Loading -> LoadingScreen()
                is LoadState.Error -> ErrorScreen()
                is LoadState.NotLoading -> {
                    BackHandler(state.employeesToSendCommand.isNotEmpty()) {
                        intentReducer(HomeScreenClickIntents.ClearEmployeesCommandsList)
                    }
                    if (results.itemSnapshotList.isNotEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(bottom = 16.dp, top = 8.dp)
                                    .background(MaterialTheme.colorScheme.background)
                                    .weight(1F),
                                verticalArrangement = Arrangement.Top,
                                content = {
                                    items(results) { command ->
                                        val isSelected = state.employeesToSendCommand.any {
                                            it == "user[${command!!.id}]"
                                        }
                                        EmployeeItem(
                                            employee = command!!,
                                            intentReducer = intentReducer,
                                            isSelected = isSelected
                                        )
                                    }
                                }
                            )
                            if (state.employeesToSendCommand.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .padding(bottom = 16.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(color = MaterialTheme.colorScheme.primary)
                                        .clickable {
                                            intentReducer(
                                                HomeScreenClickIntents.CreateCommands(
                                                    state.employeesToSendCommand
                                                )
                                            )
                                        },
                                    content = {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = stringResource(R.string.create_a_job),
                                                modifier = Modifier
                                                    .padding(vertical = 16.dp),
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    },
                                )
                            }
                        }
                    } else {
                        EmptyScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colorScheme.background)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun EmployeesScreenPreview() {
    EmployeeManagementTheme {
        EmployeesScreen(
            intentReducer = {},
            state = HomeScreenState(isDarkTheme = false)
        )
    }
}
