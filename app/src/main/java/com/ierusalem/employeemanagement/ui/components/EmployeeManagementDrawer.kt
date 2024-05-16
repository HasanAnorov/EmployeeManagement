package com.ierusalem.employeemanagement.ui.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenState
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun EmployeeManagementDrawer(
    state: HomeScreenState,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onProfileClicked: (String) -> Unit,
    onSettingsClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
    onPrivateJobsClicked: () -> Unit,
    onStatisticsClicked: () -> Unit,
    onPersonalStatisticsClicked: () -> Unit,
    onForInformationClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    EmployeeManagementTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    EmployeeManagementDrawerContent(
                        state = state,
                        onProfileClicked = onProfileClicked,
                        onSettingsClicked = onSettingsClicked,
                        onLogoutClicked = onLogoutClicked,
                        onPrivateJobClicked = onPrivateJobsClicked,
                        onStatisticsClicked = onStatisticsClicked,
                        onForInformationClick = onForInformationClick,
                        onPersonalStatisticsClicked = onPersonalStatisticsClicked
                    )
                }
            },
            content = content
        )
    }
}