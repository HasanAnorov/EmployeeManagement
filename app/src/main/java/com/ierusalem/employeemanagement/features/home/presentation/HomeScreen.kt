package com.ierusalem.employeemanagement.features.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.presentation.commands.CommandsScreen
import com.ierusalem.employeemanagement.features.home.presentation.employees.EmployeesScreen
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onDrawerClick: () -> Unit,
    intentReducer: (HomeScreenClickIntents) -> Unit,
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
            intentReducer(HomeScreenClickIntents.TabItemClick(pagerState.currentPage))
        }
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
                        onClick = {
                            intentReducer(HomeScreenClickIntents.OnThemeChange(!state.isDarkTheme))
                        },
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
                    containerColor = MaterialTheme.colorScheme.outline.copy(0.2F),
                    indicator = { },
                    divider = { },
                    tabs = {
                        state.tabItems.forEachIndexed { index, currentTab ->
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
                                    intentReducer(HomeScreenClickIntents.TabItemClick(index))
                                    scope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                text = {
                                    Text(
                                        text = currentTab.asString(context),
                                        fontSize = 16.sp,
                                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                },
                            )
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
                        0 -> CommandsScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "yuborildi"
                        )

                        1 -> CommandsScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "qabulqildi"
                        )

                        2 -> CommandsScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "bajarildi"
                        )

                        3 -> CommandsScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "bajarilmadi"
                        )

                        4 -> CommandsScreen(
                            state = state,
                            intentReducer = intentReducer,
                            status = "kechikibbajarildi"
                        )

                        5 -> {
                            EmployeesScreen(
                                intentReducer = intentReducer,
                                state = state
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun Preview_HomeScreen_Light() {
    EmployeeManagementTheme(darkTheme = false) {
        HomeScreen(
            state = HomeScreenState(
                isDarkTheme = false
            ),
            onDrawerClick = {},
            intentReducer = {}
        )
    }
}

@Preview
@Composable
fun Preview_HomeScreen_Dark() {
    EmployeeManagementTheme(darkTheme = true) {
        HomeScreen(
            state = HomeScreenState(
                isDarkTheme = true
            ),
            onDrawerClick = {},
            intentReducer = {}
        )
    }
}