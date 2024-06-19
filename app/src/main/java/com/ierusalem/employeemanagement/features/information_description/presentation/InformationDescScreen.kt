package com.ierusalem.employeemanagement.features.information_description.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.information_description.domain.InformationDescState
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.FileItemUrl
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import com.ierusalem.employeemanagement.core.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationDescScreen(
    isSent: Boolean,
    onArrowBackClick: () -> Unit,
    onDownloadFile: (String) -> Unit,
    state: InformationDescState,
    onEditInformationClicked: (Int, Int) -> Unit,
    onDeleteInformationClicked: (Int) -> Unit
) {
    if (isSent) {
        when (state.description) {
            is Resource.Loading -> LoadingScreen()
            is Resource.Success -> {
                val scrollState = rememberScrollState()
                val information = state.description.data!!.results[0]

                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    CommonTopBar(
                        containerColor = MaterialTheme.colorScheme.background,
                        onNavIconPressed = { onArrowBackClick() },
                        navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                        title = {
                            Text(
                                text = stringResource(R.string.information_description),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        },
                        actions = {
                            if(information.status != "kurildi"){
                                IconButton(onClick = { onEditInformationClicked(information.id, information.userId) }) {
                                    Icon(
                                        imageVector = Icons.Default.EditNote,
                                        contentDescription = null
                                    )
                                }
                                IconButton(onClick = { onDeleteInformationClicked(information.id) }) {
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
                                text = information.text,
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
                            if (information.file.isEmpty()) {
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
                                LazyColumn(
                                    modifier = Modifier
                                        .weight(1F)
                                        .fillMaxWidth()
                                        .padding(top = 16.dp, bottom = 8.dp)
                                        .padding(horizontal = 16.dp)
                                ) {
                                    items(information.file) { information ->
                                        FileItemUrl(
                                            modifier = Modifier.padding(top = 8.dp),
                                            file = information.file,
                                            onDownloadFile = {
                                                Log.d(
                                                    "download_url",
                                                    "InformationDescScreen: ${information.file} "
                                                )
                                                onDownloadFile(information.file)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is Resource.Failure -> ErrorScreen()
        }
    } else {
        when (state.descriptionReceived) {
            is Resource.Loading -> LoadingScreen()
            is Resource.Success -> {
                val scrollState = rememberScrollState()
                val work = state.descriptionReceived.data!!.results[0]
                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    CommonTopBar(
                        containerColor = MaterialTheme.colorScheme.background,
                        onNavIconPressed = { onArrowBackClick() },
                        navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                        title = {
                            Text(
                                text = stringResource(id = R.string.information_description),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
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
                                LazyColumn(
                                    modifier = Modifier
                                        .weight(1F)
                                        .fillMaxWidth()
                                        .padding(top = 16.dp, bottom = 8.dp)
                                        .padding(horizontal = 16.dp)
                                ) {
                                    items(work.file) {
                                        FileItemUrl(
                                            modifier = Modifier.padding(top = 8.dp),
                                            file = it.file,
                                            onDownloadFile = {
                                                onDownloadFile(it.file)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is Resource.Failure -> ErrorScreen()
        }
    }
}