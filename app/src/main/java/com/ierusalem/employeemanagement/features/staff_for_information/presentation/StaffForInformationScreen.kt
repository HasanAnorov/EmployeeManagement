package com.ierusalem.employeemanagement.features.staff_for_information.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.for_information.presentation.ForInformationContent
import com.ierusalem.employeemanagement.features.staff_for_information.domain.StaffForInformationState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffForInformationScreen(
    state: StaffForInformationState,
    intentReducer: (StaffForInformationEvents) -> Unit,
    onItemClick:(Int) -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopBar(
                containerColor = MaterialTheme.colorScheme.background,
                onNavIconPressed = {
                    intentReducer(StaffForInformationEvents.NavIconClick)
                },
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                title = {
                    Text(
                        text = stringResource(id = R.string.for_information),
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            )
        }
    ) {padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            @Suppress("DEPRECATION")

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                onRefresh = {
            intentReducer(StaffForInformationEvents.OnPullRefresh)
                }
            ) {
                ForInformationContent(
                    data = state.receivedInformation,
                    isLoading = false,
                    onPullRefresh = {intentReducer(StaffForInformationEvents.OnPullRefresh)},
                    onItemClick = {
                        onItemClick(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    EmployeeManagementTheme {
        StaffForInformationScreen(
            state = StaffForInformationState(),
            intentReducer = {},
            onItemClick = {}
        )
    }
}