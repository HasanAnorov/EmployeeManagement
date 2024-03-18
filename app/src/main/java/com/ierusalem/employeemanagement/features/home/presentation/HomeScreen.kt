package com.ierusalem.employeemanagement.features.home.presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onDrawerClick: () -> Unit
) {

    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)

    Scaffold(
        topBar = {
            CommonTopBar(
                modifier = Modifier.shadow(elevation = 6.dp),
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

    }
}

@Preview
@Composable
fun Preview_HomeScreen_Light() {
    EmployeeManagementTheme(darkTheme = false) {
        HomeScreen(
            state = HomeScreenState(),
            onDrawerClick = {}
        )
    }
}

@Preview
@Composable
fun Preview_HomeScreen_Dark() {
    EmployeeManagementTheme(darkTheme = true) {
        HomeScreen(
            state = HomeScreenState(),
            onDrawerClick = {}
        )
    }
}