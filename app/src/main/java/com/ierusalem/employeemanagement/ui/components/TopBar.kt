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
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = title,
        navigationIcon = {
            IconButton(onClick = { onNavIconPressed() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = actions,
        scrollBehavior = scrollBehavior,
    )

//    CenterAlignedTopAppBar(
//        modifier = modifier,
//        actions = actions,
//        title = title,
//        scrollBehavior = scrollBehavior,
//        navigationIcon = {
//            IconButton(onClick = { onNavIconPressed() }) {
//                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
//            }
//        }
//    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CommonTopBarPreview() {
    EmployeeManagementTheme {
        CommonTopBar(title = { Text("Preview!") })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CommonTopBarPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        CommonTopBar(title = { Text("Preview!") })
    }
}