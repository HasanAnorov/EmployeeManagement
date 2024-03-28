package com.ierusalem.employeemanagement.features.home.presentation.employees

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.Result
import com.ierusalem.employeemanagement.ui.components.EmptyScreen
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun EmployeesScreen(
    modifier: Modifier = Modifier,
    intentReducer: (HomeScreenClickIntents) -> Unit,
    state: Flow<PagingData<Result>>
) {
    val results = state.collectAsLazyPagingItems()
    when (results.loadState.refresh) {
        is LoadState.Loading -> LoadingScreen()
        is LoadState.Error -> ErrorScreen()
        is LoadState.NotLoading -> {
            if (results.itemSnapshotList.isNotEmpty()){
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        content = {
                            items(results) { command ->
                                EmployeeItem(
                                    employee = command!!,
                                    intentReducer = intentReducer
                                )
                            }
                        }
                    )
                }
            }else{
                EmptyScreen()
            }
        }
    }
}

//@Preview
//@Composable
//fun EmployeesScreenPreview() {
//    EmployeeManagementTheme {
//        EmployeesScreen(
//            intentReducer = {},
//            events = LazyPagingItems()
//        )
//    }
//}
//
//@Preview
//@Composable
//fun EmployeesScreenPreviewDark() {
//    EmployeeManagementTheme(darkTheme = true) {
//        EmployeesScreen(
//            intentReducer = {},
//            events = LazyPagingItems()
//        )
//    }
//}