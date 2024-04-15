package com.ierusalem.employeemanagement.features.compose.presentation

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeScreen(
    onAttachFileClick: () -> Unit,
    onNavIconClicked: () -> Unit,
    onSubmitClicked: () -> Unit,
    onTextChanged: (String) -> Unit,
    onYearChanged: (String) -> Unit,
    onMonthChanged: (String) -> Unit,
    onDayChanged: (String) -> Unit,
    state: ComposeScreenState
) {
    val yearMaxChar = 4
    val monthMaxChar = 2
    val dayMaxChar = 2

    BackHandler {
        onNavIconClicked()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CommonTopBar(
            containerColor = MaterialTheme.colorScheme.background,
            onNavIconPressed = { onNavIconClicked() },
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            title = {
                Text(
                    text = stringResource(R.string.create_a_command),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        )
        Text(
            modifier = Modifier.padding(start = 32.dp),
            text = stringResource(R.string.command_description),
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
                onTextChanged(it)
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.type_here),
                    style = MaterialTheme.typography.labelSmall
                )
            },
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1.5F)) {
                Text(
                    modifier = Modifier.padding(start = 32.dp),
                    text = stringResource(R.string.year),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                TextField(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .padding(top = 8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    minLines = 1,
                    maxLines = 1,
                    shape = RoundedCornerShape(12.dp),
                    textStyle = MaterialTheme.typography.titleSmall,
                    value = state.yearForm,
                    onValueChange = {
                        if (it.length <= yearMaxChar) onYearChanged(it)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.year),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                )
            }
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(R.string.month),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                TextField(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .padding(top = 8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    minLines = 1,
                    maxLines = 1,
                    shape = RoundedCornerShape(12.dp),
                    textStyle = MaterialTheme.typography.titleSmall,
                    value = state.monthForm,
                    onValueChange = {
                        if (it.length <= monthMaxChar) onMonthChanged(it)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.month),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                )
            }
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(R.string.day),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                TextField(
                    modifier = Modifier
                        .padding(end = 16.dp, start = 8.dp)
                        .padding(top = 8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    minLines = 1,
                    maxLines = 1,
                    shape = RoundedCornerShape(12.dp),
                    textStyle = MaterialTheme.typography.titleSmall,
                    value = state.dayForm,
                    onValueChange = {
                        if (it.length <= dayMaxChar) onDayChanged(it)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.day),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.primary)
                .clickable { onAttachFileClick() },
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
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.primary)
                .clickable {
                    if (!state.isSubmitting)
                        onSubmitClicked()
                },
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val submitString =
                        if (state.isSubmitting) stringResource(R.string.submitting) else stringResource(
                            id = R.string.submit
                        )
                    Text(
                        text = submitString,
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }
            },
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
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

@Preview
@Composable
fun ComposeScreenPreview() {
    EmployeeManagementTheme {
        ComposeScreen(
            onAttachFileClick = {},
            onNavIconClicked = {},
            onSubmitClicked = {},
            onTextChanged = {},
            onYearChanged = {},
            onMonthChanged = {},
            onDayChanged = {},
            state = ComposeScreenState()
        )
    }
}

@Preview
@Composable
fun ComposeScreenPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        ComposeScreen(
            onAttachFileClick = {},
            onNavIconClicked = {},
            onSubmitClicked = {},
            onTextChanged = {},
            onYearChanged = {},
            onMonthChanged = {},
            onDayChanged = {},
            state = ComposeScreenState()
        )
    }
}