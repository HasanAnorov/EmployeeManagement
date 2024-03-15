package com.ierusalem.employeemanagement.ui.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@Composable
fun EmployeeManagementDrawer(
    username: String,
    imageUrl: String,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onProfileClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    EmployeeManagementTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    EmployeeManagementDrawerContent(
                        username = username,
                        imageUrl = imageUrl,
                        onProfileClicked = onProfileClicked,
                    )
                }
            },
            content = content
        )
    }
}