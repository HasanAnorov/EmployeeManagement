package com.ierusalem.employeemanagement.features.for_information.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.for_information.domain.ForInformationState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.components.EmptyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForInformationScreen(
    state: ForInformationState,
    intentReducer: (ForInformationEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            CommonTopBar(
                containerColor = MaterialTheme.colorScheme.background,
                onNavIconPressed = {
                    intentReducer(ForInformationEvents.NavIconClick)
                },
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                title = {
                    Text(
                        text = stringResource(id = R.string.statistics),
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            @Suppress("DEPRECATION")
            (SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
        onRefresh = {
            intentReducer(ForInformationEvents.OnPullRefresh)
        }
    ) {
        val data = state.data
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
//                    itemsIndexed(data) { index, command ->
//                        var image = command.adminImage
//                        if (image.startsWith("/media")) {
//                            image = "${Constants.BASE_URL}${command.adminImage}"
//                        }
//                        WorkItem(
//                            modifier = Modifier
//                                .padding(horizontal = 16.dp),
//                            image = image,
//                            position = command.adminPosition
//                                ?: stringResource(id = R.string.not_given),
//                            fullName = "${command.adminUsername} ${command.adminLastname}",
//                            description = command.text,
//                            deadline = command.endTime,
//                            onItemClick = {
//                                intentReducer(HomeScreenClickIntents.OnItemClick(command.workId.toString()))
//                            }
//                        )
//                        if (index < data.size - 1) {
//                            HorizontalDivider(
//                                modifier = Modifier
//                                    .padding(
//                                        horizontal = 16.dp,
//                                        vertical = 16.dp
//                                    )
//                                    .clip(RoundedCornerShape(1.dp))
//                                    .background(MaterialTheme.colorScheme.outline)
//                            )
//                        }
//                    }
                }
            )
        }
    })
        }
    }
}