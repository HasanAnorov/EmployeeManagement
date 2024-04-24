package com.ierusalem.employeemanagement.features.private_jobs.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.private_jobs.domain.PrivateJobsState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PrivateJobsScreen(
    state: PrivateJobsState,
    intentReducer: (PrivateJobsEvents) -> Unit
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = state.selectedTabIndex,
        pageCount = { state.tabItems.size })
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (pagerState.isScrollInProgress) {
            intentReducer(PrivateJobsEvents.TabItemClick(pagerState.currentPage))
        }
    }

    var countSent by rememberSaveable {
        mutableIntStateOf(state.commandsSent.size)
    }
    LaunchedEffect(key1 = state.commandsSent.size) {
        countSent = state.commandsSent.size
    }
    var countReceived by rememberSaveable {
        mutableIntStateOf(state.commandsReceived.size)
    }
    LaunchedEffect(key1 = state.commandsReceived.size) {
        countReceived = state.commandsReceived.size
    }
    var countDone by rememberSaveable {
        mutableIntStateOf(state.commandsDone.size)
    }
    LaunchedEffect(key1 = state.commandsDone.size) {
        countDone = state.commandsDone.size
    }
    var countNotDone by rememberSaveable {
        mutableIntStateOf(state.commandsNotDone.size)
    }
    LaunchedEffect(key1 = state.commandsNotDone.size) {
        countNotDone = state.commandsNotDone.size
    }
    var countLateDone by rememberSaveable {
        mutableIntStateOf(state.commandsLateDone.size)
    }
    LaunchedEffect(key1 = state.commandsLateDone.size) {
        countLateDone = state.commandsLateDone.size
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CommonTopBar(
            containerColor = MaterialTheme.colorScheme.background,
            onNavIconPressed = {
                intentReducer(PrivateJobsEvents.NavIconClick)
            },
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            title = {
                Text(
                    text = stringResource(id = R.string.private_jobs),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        )
        ScrollableTabRow(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(9.dp))
                .fillMaxWidth(),
            edgePadding = 0.dp,
            selectedTabIndex = state.selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.outline.copy(0.2F),
            indicator = { },
            divider = { },
            tabs = {
                state.tabItems.forEachIndexed { index, currentTab ->
                    val data = when (index) {
                        0 -> countSent
                        1 -> countReceived
                        2 -> countDone
                        3 -> countNotDone
                        4 -> countLateDone
                        else -> 0
                    }
                    Tab(
                        modifier = Modifier
                            .background(
                                color = if (pagerState.currentPage == index)
                                    MaterialTheme.colorScheme.primary
                                else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        selected = state.selectedTabIndex == index,
                        selectedContentColor = MaterialTheme.colorScheme.onBackground,
                        unselectedContentColor = MaterialTheme.colorScheme.onBackground.copy(
                            0.5F
                        ),
                        onClick = {
                            val status = when (index) {
                                0 -> "yuborildi"
                                1 -> "qabulqildi"
                                2 -> "bajarildi"
                                3 -> "bajarilmadi"
                                4 -> "kechikibbajarildi"
                                else -> "yuborildi"
                            }
                            intentReducer(PrivateJobsEvents.OnPullToRefreshCommands(status))
                            intentReducer(PrivateJobsEvents.TabItemClick(index))
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        content = {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 10.dp,
                                    vertical = 8.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 6.dp),
                                    text = currentTab.asString(context),
                                    fontSize = 16.sp,
                                    color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.titleSmall
                                )
                                if (data > 0) {
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 4.dp, vertical = 4.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .size(24.dp)
                                            .background(
                                                MaterialTheme.colorScheme.primaryContainer.copy(
                                                    0.5F
                                                )
                                            ),
                                        contentAlignment = Alignment.Center,
                                        content = {
                                            Text(
                                                text = data.toString(),
                                                fontSize = 16.sp,
                                                color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                        }
                                    )
                                }
                            }
                        },
                    )
                }
            }
        )
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = pagerState
        ) { pageCount ->
            when (pageCount) {
                0 -> PrivateCommandsScreen(
                    state = state, intentReducer = intentReducer, status = "yuborildi"
                )

                1 -> PrivateCommandsScreen(
                    state = state, intentReducer = intentReducer, status = "qabulqildi"
                )

                2 -> PrivateCommandsScreen(
                    state = state, intentReducer = intentReducer, status = "bajarildi"
                )

                3 -> PrivateCommandsScreen(
                    state = state, intentReducer = intentReducer, status = "bajarilmadi"
                )
                4 -> PrivateCommandsScreen(
                    state = state,
                    intentReducer = intentReducer,
                    status = "kechikibbajarildi"
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_PrivateJosb_Light() {
    EmployeeManagementTheme(darkTheme = false) {
        PrivateJobsScreen(
            state = PrivateJobsState(),
            intentReducer = {}
        )
    }
}