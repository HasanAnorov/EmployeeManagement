package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    titleColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    navigationIcon: ImageVector = Icons.Filled.Menu,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = titleColor,
        ),
        title = title,
        navigationIcon = {
            IconButton(onClick = { onNavIconPressed() }) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = null
                )
            }
        },
        actions = actions,
        scrollBehavior = scrollBehavior,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CommonTopBarPreview() {
    EmployeeManagementTheme {
        CommonTopBar(
            title = {
                Text(
                    text = "Employee Management",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CommonTopBarPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        CommonTopBar(
            title = {
                Text(
                    text = "Employee Management",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        )
    }
}