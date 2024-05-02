package com.ierusalem.employeemanagement.ui.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun EmployeeManagementStaffDrawer(
    username: String,
    imageUrl: String,
    email: String,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onProfileClicked: (String) -> Unit,
    onSettingsClicked: () -> Unit,
    onForInformationClick: () -> Unit,
    onLogoutClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    EmployeeManagementTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                        EmployeeManagementStaffDrawerContent(
                            username = username,
                            imageUrl = imageUrl,
                            email = email,
                            onProfileClicked = onProfileClicked,
                            onSettingsClicked = onSettingsClicked,
                            onLogoutClicked = onLogoutClicked,
                            onForInformationClick = onForInformationClick
                        )
                }

            },
            content = content
        )
    }
}