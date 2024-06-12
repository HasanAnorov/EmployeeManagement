package com.ierusalem.employeemanagement.features.work_description.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.create_work.presentation.AlertDialogExample
import com.ierusalem.employeemanagement.features.create_work.presentation.FileItem
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionScreenState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.FileItemUrl
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.core.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkDescriptionScreen(
    state: WorkDescriptionScreenState,
    dismissDialog: () -> Unit,
    gotoStorageSetting: () -> Unit,
    onEditWorkClicked: () -> Unit,
    onDeleteWorkClicked: () -> Unit,
    intentReducer: (WorkDescriptionScreenEvents) -> Unit,
) {
    when (state.workItem) {
        is Resource.Loading -> LoadingScreen()
        is Resource.Success -> {
            val scrollState = rememberScrollState()
            val work = state.workItem.data!!.results[0]
            if (state.showAlertDialog) {
                AlertDialogExample(
                    onDismissRequest = { dismissDialog() },
                    onConfirmation = { gotoStorageSetting() },
                    dialogTitle = stringResource(R.string.give_permission_to_use_data),
                    dialogText = stringResource(R.string.allow_the_app_to_use_device_data),
                )
            }
            Column(
                modifier = Modifier
                    .navigationBarsPadding()
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
                    },
                    actions = {
                        if(state.isFromSent){
                            IconButton(onClick = { onEditWorkClicked() }) {
                                Icon(imageVector = Icons.Default.EditNote, contentDescription = null)
                            }
                            IconButton(onClick = { onDeleteWorkClicked() }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                            }
                        }
                    }
                )
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .verticalScroll(scrollState)
                ) {
                    Column(modifier = Modifier.weight(1F)) {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = stringResource(R.string.command_description),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                            text = work.text,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.outline,
                            style = MaterialTheme.typography.titleSmall,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                            text = stringResource(R.string.deadline),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            style = MaterialTheme.typography.titleSmall,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                            text = work.endTime,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.outline,
                            style = MaterialTheme.typography.titleSmall,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                            text = stringResource(R.string.attached_files),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (work.file.isEmpty()) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 8.dp),
                                text = stringResource(R.string.no_files_provided),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                lineHeight = 20.sp,
                                color = MaterialTheme.colorScheme.outline,
                                style = MaterialTheme.typography.titleSmall,
                                overflow = TextOverflow.Ellipsis
                            )
                        } else {
                            val customModifier =
                                if (state.isFromHome) Modifier.height(240.dp) else Modifier.height(
                                    300.dp
                                )
                            LazyColumn(
                                modifier = customModifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp, bottom = 8.dp)
                                    .padding(horizontal = 16.dp)
                            ) {
                                items(work.file) {
                                    FileItemUrl(
                                        modifier = Modifier.padding(top = 8.dp),
                                        file = it.file,
                                        onDownloadFile = {
                                            intentReducer(
                                                WorkDescriptionScreenEvents.DownloadFile(
                                                    it.file
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                    if (!state.isFromHome) {
                        val custom = if (state.files.isNotEmpty()) {
                            Modifier.weight(1F)
                        } else Modifier
                        Column(modifier = custom) {
                            val weighCustomModifier =
                                if (work.file.isEmpty() && state.files.isEmpty()) Modifier.weight(1F) else Modifier
                            Text(
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 16.dp),
                                text = stringResource(R.string.additional_information),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.outline
                            )
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 8.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                minLines = 6,
                                maxLines = 8,
                                shape = RoundedCornerShape(12.dp),
                                textStyle = MaterialTheme.typography.titleSmall,
                                value = state.textForm,
                                onValueChange = {
                                    intentReducer(WorkDescriptionScreenEvents.OnTextChanged(it))
                                },
                                placeholder = {
                                    Text(
                                        text = stringResource(R.string.type_here),
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                },
                            )
                            Spacer(modifier = weighCustomModifier)
                            if (state.files.isNotEmpty()) {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp)
                                        .padding(top = 16.dp, bottom = 8.dp)
                                        .padding(horizontal = 16.dp)
                                ) {
                                    items(state.files) {
                                        FileItem(
                                            modifier = Modifier.padding(top = 8.dp),
                                            file = it
                                        )
                                    }
                                }
                            }
                        }
                    }
                    if (state.isFromHome) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)
                                .padding(top = 16.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start,
                            content = {
                                if (work.textEmployee?.isNotEmpty() == true || work.fileEmployee.isNotEmpty())
                                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                                if (work.textEmployee?.isNotEmpty() == true) {
                                    Text(
                                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                                        text = stringResource(id = R.string.additional_information),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(start = 16.dp, top = 8.dp),
                                        text = work.textEmployee,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Normal,
                                        maxLines = 10,
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                                if (work.fileEmployee.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                                        text = stringResource(R.string.additional_files),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1F)
                                            .padding(top = 16.dp, bottom = 8.dp)
                                            .padding(horizontal = 16.dp)
                                    ) {
                                        items(work.fileEmployee) {
                                            Log.d("ahi3646", "WorkDescriptionScreen: ${it.file} ")
                                            FileItemUrl(
                                                modifier = Modifier.padding(top = 8.dp),
                                                file = it.file,
                                                onDownloadFile = {
                                                    intentReducer(
                                                        WorkDescriptionScreenEvents.DownloadFile(
                                                            it.file
                                                        )
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
                if (!state.isFromHome) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 24.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = MaterialTheme.colorScheme.primary)
                            .clickable {
                                intentReducer(WorkDescriptionScreenEvents.OnAttachFilesClick)
                            },
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.attach_a_file),
                                    modifier = Modifier
                                        .padding(vertical = 16.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    textAlign = TextAlign.Center
                                )
                                Icon(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .rotate(30F),
                                    imageVector = Icons.Default.AttachFile,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        },
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp, bottom = 24.dp)
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
            intentReducer = {},
            dismissDialog = {},
            gotoStorageSetting = {},
            onEditWorkClicked = {},
            onDeleteWorkClicked = {}
        )
    }
}

@Preview
@Composable
private fun WorkDescriptionScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        WorkDescriptionScreen(
            state = WorkDescriptionScreenState(),
            intentReducer = {},
            dismissDialog = {},
            gotoStorageSetting = {},
            onEditWorkClicked = {},
            onDeleteWorkClicked = {}
        )
    }
}