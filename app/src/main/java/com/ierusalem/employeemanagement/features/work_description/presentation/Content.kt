//package com.ierusalem.employeemanagement.features.work_description.presentation
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.AttachFile
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.ierusalem.employeemanagement.R
//import com.ierusalem.employeemanagement.ui.components.CommonTopBar
//import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Content() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//    ) {
//        CommonTopBar(
//            containerColor = MaterialTheme.colorScheme.background,
//            onNavIconPressed = { },
//            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
//            title = {
//                Text(
//                    text = stringResource(id = R.string.work_description),
//                    style = MaterialTheme.typography.titleSmall,
//                    color = MaterialTheme.colorScheme.onPrimaryContainer,
//                )
//            }
//        )
//        Text(
//            modifier = Modifier.padding(start = 16.dp),
//            text = stringResource(R.string.command_description),
//            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colorScheme.outline
//        )
//        Text(
//            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
//            text = "This is a work description which you have been asked for many times",
//            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//        Text(
//            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
//            text = stringResource(R.string.deadline),
//            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colorScheme.outline
//        )
//        Text(
//            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
//            text = "20.03.2024",
//            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//        Text(
//            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
//            text = stringResource(R.string.attached_files),
//            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colorScheme.outline
//        )
//        if (true) {
//            Text(
//                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
//                text = "Fayllar taqdim qilinmagan",
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.onBackground
//            )
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1F)
//                    .padding(top = 16.dp, bottom = 8.dp)
//                    .padding(horizontal = 16.dp)
//            ) {
////                items(work.file) {
////                    FileItemUrl(
////                        modifier = Modifier.padding(top = 8.dp),
////                        file = it.file,
////                        onDownloadFile = {
//////                            intentReducer(WorkDescriptionScreenEvents.DownloadFile(it.file))
////                        }
////                    )
////                }
//            }
//        }
//        if (false) {
//            Text(
//                modifier = Modifier.padding(start = 32.dp),
//                text = stringResource(R.string.additional_information),
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.outline
//            )
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 8.dp),
//                colors = TextFieldDefaults.colors(
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    disabledIndicatorColor = Color.Transparent
//                ),
//                minLines = 6,
//                maxLines = 8,
//                shape = RoundedCornerShape(12.dp),
//                textStyle = MaterialTheme.typography.titleSmall,
//                value = "",
//                onValueChange = {
////                onTextChanged(it)
//                },
//                placeholder = {
//                    Text(
//                        text = stringResource(R.string.type_here),
//                        style = MaterialTheme.typography.labelSmall
//                    )
//                },
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 24.dp)
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(color = MaterialTheme.colorScheme.primary)
//                    .clickable {
////                    onAttachFileClick()
//                    },
//                content = {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = stringResource(R.string.attach_a_file),
//                            modifier = Modifier
//                                .padding(vertical = 16.dp),
//                            style = MaterialTheme.typography.labelSmall,
//                            color = MaterialTheme.colorScheme.onPrimary,
//                            textAlign = TextAlign.Center
//                        )
//                        Icon(
//                            modifier = Modifier
//                                .padding(start = 8.dp)
//                                .rotate(30F),
//                            imageVector = Icons.Default.AttachFile,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.onPrimary
//                        )
//                    }
//                },
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 16.dp, bottom = 24.dp)
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(color = MaterialTheme.colorScheme.primary)
//                    .clickable { },
//                content = {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = stringResource(R.string.mark_work_as_done),
//                            modifier = Modifier
//                                .padding(vertical = 16.dp),
//                            style = MaterialTheme.typography.labelSmall,
//                            color = MaterialTheme.colorScheme.onPrimary,
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                },
//            )
//        }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.Start,
//            content = {
//                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
//                Text(
//                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
//                    text = "Qo'shimcha ma'lumotlar",
//                    style = MaterialTheme.typography.labelSmall,
//                    color = MaterialTheme.colorScheme.outline
//                )
//                Text(
//                    modifier = Modifier
//                        .padding(start = 16.dp, top = 8.dp)
//                        .clickable {
//                            // add copy text feature
//                        },
//                    text = "Bu yerda xodim tomonidan yuborilgan qo'shimcha ma'lumotlar joylashadi",
//                    style = MaterialTheme.typography.labelSmall,
//                    maxLines = 10,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
//                Text(
//                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
//                    text = "Qo'shimcha fayllar",
//                    style = MaterialTheme.typography.labelSmall,
//                    color = MaterialTheme.colorScheme.outline
//                )
//                if (true) {
//                    Text(
//                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
//                        text = "Fayllar taqdim qilinmagan",
//                        style = MaterialTheme.typography.labelSmall,
//                        color = MaterialTheme.colorScheme.onBackground
//                    )
//                } else {
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1F)
//                            .padding(top = 16.dp, bottom = 8.dp)
//                            .padding(horizontal = 16.dp)
//                    ) {
////                items(work.file) {
////                    FileItemUrl(
////                        modifier = Modifier.padding(top = 8.dp),
////                        file = it.file,
////                        onDownloadFile = {
//////                            intentReducer(WorkDescriptionScreenEvents.DownloadFile(it.file))
////                        }
////                    )
////                }
//                    }
//                }
//            }
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun ContentPreview() {
//    EmployeeManagementTheme {
//        Content()
//    }
//}