package com.ierusalem.employeemanagement.features.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.features.home.presentation.commands.ComposeScreen
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onDrawerClick: () -> Unit,
    intentReducer: (HomeScreenClickIntents) -> Unit
) {

    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        state.tabItems.size
    }
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
                        text = "EmployeeManagement",
                        style = MaterialTheme.typography.titleSmall
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
                TabRow(
                    modifier = Modifier.fillMaxWidth(),
                    selectedTabIndex = state.selectedTabIndex,
                    indicator = { tabPositions ->
                        if (state.selectedTabIndex < tabPositions.size) {
                            SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[state.selectedTabIndex]),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    tabs = {
                        state.tabItems.forEachIndexed { index, currentTab ->
                            Tab(
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
                                        text = currentTab,
                                        fontSize = 16.sp,
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
                        0 -> ComposeScreen(intentReducer = intentReducer)

                        1 -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                                content = {
                                    Text(text = "Xodimlar")
                                }
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
            state = HomeScreenState(),
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
            state = HomeScreenState(),
            onDrawerClick = {},
            intentReducer = {}
        )
    }
}