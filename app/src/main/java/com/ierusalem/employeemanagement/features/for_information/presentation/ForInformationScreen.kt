package com.ierusalem.employeemanagement.features.for_information.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.for_information.domain.ForInformationState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ForInformationScreen(
    state: ForInformationState,
    intentReducer: (ForInformationEvents) -> Unit,
    onItemClick:(Int) ->Unit,
    onItemClickSent:(Int) ->Unit
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
                        text = stringResource(id = R.string.for_information),
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            )
        }
    ) { paddingValues ->
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(
            initialPage = state.selectedTabIndex,
            pageCount = { state.tabItems.size }
        )

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                intentReducer(ForInformationEvents.OnTabItemClick(page))
            }
        }

        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(
                modifier = Modifier
                    .fillMaxWidth(),
                selectedTabIndex = state.selectedTabIndex,
                indicator = { tabPositions ->
                    if (state.selectedTabIndex < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[state.selectedTabIndex]),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                contentColor = MaterialTheme.colorScheme.background,
                containerColor = MaterialTheme.colorScheme.background,
                divider = {},
                tabs = {
                    state.tabItems.forEachIndexed { index, currentTab ->
                        Tab(
                            selected = state.selectedTabIndex == index,
                            selectedContentColor = MaterialTheme.colorScheme.onBackground,
                            unselectedContentColor = MaterialTheme.colorScheme.onBackground.copy(
                                0.5F
                            ),
                            onClick = {
                                scope.launch {
                                    //intentReducer(HomeScreenClickIntents.TabItemClicked(index))
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 8.dp
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = currentTab.asString(context),
                                        fontSize = 16.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.titleSmall
                                    )

                                    if (index == 1  && state.receivedBadgeCount > 0) {
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
                                                    text = state.receivedBadgeCount.toString(),
                                                    fontSize = 16.sp,
                                                    color = MaterialTheme.colorScheme.onBackground,
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
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
            ) { pageCount ->
                when (pageCount) {
                    0 -> ForInformationContent(
                        data = state.sentInformation,
                        isLoading = state.isLoading,
                        onPullRefresh = {
                            intentReducer(ForInformationEvents.OnPullRefresh)
                        },
                        onItemClick = {
                            onItemClickSent(it)
                        }
                    )

                    1 -> ForInformationContent(
                        data = state.receivedInformation,
                        isLoading = state.isLoading,
                        onPullRefresh = {
                            intentReducer(ForInformationEvents.OnPullRefresh)
                        },
                        onItemClick = {
                            onItemClick(it)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLight() {
    EmployeeManagementTheme {
        ForInformationScreen(
            state = ForInformationState(),
            intentReducer = {},
            onItemClick = {},
            onItemClickSent = {}
        )
    }
}
