package com.ierusalem.employeemanagement.features.personal_statistics.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.personal_statistics.domain.PersonalStatisticsState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PersonalStatisticsScreen(
    state: PersonalStatisticsState,
    intentReducer: (PersonalStatisticsEvents) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = state.selectedTabIndex,
        pageCount = { state.tabItems.size })
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (pagerState.isScrollInProgress) {
            intentReducer(PersonalStatisticsEvents.TabItemClick(pagerState.currentPage))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CommonTopBar(
            containerColor = MaterialTheme.colorScheme.background,
            onNavIconPressed = {
                intentReducer(PersonalStatisticsEvents.OnNavIconClick)
            },
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            title = {
                Text(
                    text = stringResource(id = R.string.personal_statistics),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        )
        Column(
            modifier = Modifier
                .weight(1F),
            content = {
                TabRow(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .fillMaxWidth(),
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
                                    intentReducer(PersonalStatisticsEvents.TabItemClick(index))
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
                                            text = currentTab.asString(),
                                            fontSize = 16.sp,
                                            color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                                            style = MaterialTheme.typography.titleSmall
                                        )
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
                        0 -> {

                        }

                        1 -> {

                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    EmployeeManagementTheme {
        PersonalStatisticsScreen(
            state = PersonalStatisticsState(),
            intentReducer = {}
        )
    }
}