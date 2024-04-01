package com.ierusalem.employeemanagement.features.work_description.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionScreenState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.FileItemUrl
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkDescriptionScreen(
    state: WorkDescriptionScreenState,
    intentReducer: (WorkDescriptionScreenEvents) -> Unit,
) {
    when(state.workItem){
        is Resource.Loading -> LoadingScreen()
        is Resource.Success -> {
            val work = state.workItem.data!!.results[0]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                CommonTopBar(
                    containerColor = MaterialTheme.colorScheme.background,
                    onNavIconPressed = { intentReducer(WorkDescriptionScreenEvents.NavIconClick) },
                    navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    title = {
                        Text(
                            text = stringResource(id = R.string.work_description),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.command_description),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = work.text,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = stringResource(R.string.deadline),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = work.endTime,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = stringResource(R.string.attached_files),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .padding(top = 16.dp, bottom = 8.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    items(work.file) {
                        FileItemUrl(
                            modifier = Modifier.padding(top = 8.dp),
                            file = it.file,
                            onDownloadFile = {
                                intentReducer(WorkDescriptionScreenEvents.DownloadFile(it.file))
                            }
                        )
                    }
                }
                if (!state.isFromHome){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 16.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = MaterialTheme.colorScheme.primary)
                            .clickable { intentReducer(WorkDescriptionScreenEvents.MarkAsDone(work.id.toString())) },
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.mark_work_as_done),
                                    modifier = Modifier
                                        .padding(vertical = 16.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        },
                    )
                }
            }
        }
        is Resource.Failure -> ErrorScreen()
    }

}

@Preview
@Composable
private fun WorkDescriptionScreenPreview() {
    EmployeeManagementTheme {
        WorkDescriptionScreen(
            state = WorkDescriptionScreenState(),
            intentReducer = {}
        )
    }
}

@Preview
@Composable
private fun WorkDescriptionScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        WorkDescriptionScreen(
            state = WorkDescriptionScreenState(),
            intentReducer = {}
        )
    }
}