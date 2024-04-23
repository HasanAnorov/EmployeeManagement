package com.ierusalem.employeemanagement.features.staff_home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
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
import com.ierusalem.employeemanagement.features.staff_home.domain.StaffHomeScreenState
import com.ierusalem.employeemanagement.features.staff_home.presentation.commands_screen.StaffComposeScreen
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StaffHomeScreen(
    state: StaffHomeScreenState,
    intentReducer: (StaffHomeScreenEvents) -> Unit,
    onDrawerClick: () -> Unit
) {
    val context = LocalContext.current
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = state.selectedTabIndex,
        pageCount = { state.tabItems.size }
    )
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (pagerState.isScrollInProgress) {
            intentReducer(StaffHomeScreenEvents.TabItemClick(pagerState.currentPage))
        }
    }
    var countSent by rememberSaveable {
        mutableIntStateOf(state.commandsSent.data?.size ?: 0)
    }
    LaunchedEffect(key1 = state.commandsSent.data?.size ?: 0) {
        countSent = state.commandsSent.data?.size ?: 0
    }
    var countReceived by rememberSaveable {
        mutableIntStateOf(state.commandsReceived.data?.size ?: 0)
    }
    LaunchedEffect(key1 = state.commandsReceived.data?.size ?: 0) {
        countReceived = state.commandsReceived.data?.size ?: 0
    }
    var countDone by rememberSaveable {
        mutableIntStateOf(state.commandsDone.data?.size ?: 0)
    }
    LaunchedEffect(key1 = state.commandsDone.data?.size ?: 0) {
        countDone = state.commandsDone.data?.size ?: 0
    }
    var countNotDone by rememberSaveable {
        mutableIntStateOf(state.commandsNotDone.data?.size ?: 0)
    }
    LaunchedEffect(key1 = state.commandsNotDone.data?.size ?: 0) {
        countNotDone = state.commandsNotDone.data?.size ?: 0
    }
    var countLateDone by rememberSaveable {
        mutableIntStateOf(state.commandsLateDone.data?.size ?: 0)
    }
    LaunchedEffect(key1 = state.commandsLateDone.data?.size ?: 0) {
        countLateDone = state.commandsLateDone.data?.size ?: 0
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                titleColor = MaterialTheme.colorScheme.onBackground,
                containerColor = MaterialTheme.colorScheme.background,
                onNavIconPressed = { onDrawerClick() },
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                actions = {
                    IconButton(
                        onClick = { intentReducer(StaffHomeScreenEvents.OnThemeChange(!state.isDarkTheme)) },
                        content = {
                            val icon =
                                if (state.isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    )
                }
            )
        },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            content = {
                ScrollableTabRow(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .fillMaxWidth(),
                    edgePadding = 0.dp,
                    selectedTabIndex = state.selectedTabIndex,
                    indicator = { },
                    divider = {},
                    containerColor = MaterialTheme.colorScheme.outline.copy(0.2F),
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
                                        3 -> "kechikibbajarildi"
                                        4 -> "bajarilmadi"
                                        else -> "yuborildi"
                                    }
                                    intentReducer(StaffHomeScreenEvents.OnPullToRefreshCommands(status))
                                    intentReducer(StaffHomeScreenEvents.TabItemClick(index))
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
//                            Tab(
//                                modifier = Modifier.background(
//                                    color = if (pagerState.currentPage == index)
//                                        MaterialTheme.colorScheme.primary
//                                    else Color.Transparent,
//                                    shape = RoundedCornerShape(12.dp)
//                                ),
//                                selected = state.selectedTabIndex == index,
//                                selectedContentColor = MaterialTheme.colorScheme.onBackground,
//                                unselectedContentColor = MaterialTheme.colorScheme.onBackground.copy(
//                                    0.5F
//                                ),
//                                onClick = {
//                                    intentReducer(StaffHomeScreenEvents.TabItemClick(index))
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(index)
//                                    }
//                                },
//                                text = {
//                                    Text(
//                                        text = currentTab.asString(context),
//                                        fontSize = 16.sp,
//                                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
//                                        style = MaterialTheme.typography.titleSmall
//                                    )
//                                },
//                            )
                        }
                    }
                )
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageCount ->
                    when (pageCount) {
                        0 -> StaffComposeScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "yuborildi"
                        )

                        1 -> StaffComposeScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "qabulqildi"
                        )

                        2 -> StaffComposeScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "bajarildi"
                        )

                        3 -> StaffComposeScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "kechikibbajarildi"
                        )
                        4 -> StaffComposeScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "bajarilmadi"
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun StaffHomeScreenPreview() {
    EmployeeManagementTheme {
        StaffHomeScreen(
            state = StaffHomeScreenState(
                false
            ),
            onDrawerClick = {},
            intentReducer = {}
        )
    }
}

@Preview
@Composable
fun StaffHomeScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        StaffHomeScreen(
            state = StaffHomeScreenState(
                true
            ),
            onDrawerClick = {},
            intentReducer = {}
        )
    }
}