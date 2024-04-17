package com.ierusalem.employeemanagement.features.home.presentation.employees

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.Result
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EmployeeItem(
    modifier: Modifier = Modifier,
    employee: Result,
    intentReducer: (HomeScreenClickIntents) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        shape = ShapeDefaults.Medium,
        onClick = { }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            GlideImage(
                failure = { },
                imageModel = { employee.image },
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(vertical = 4.dp)
                    .size(width = 48.dp, height = 48.dp)
                    .clip(CircleShape),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart,
                    contentDescription = null
                )
            )
            Column(
                modifier = Modifier.weight(1F)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .padding(top = 8.dp),
                    text = employee.username + " " + employee.lastName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                Row {
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = "${stringResource(id = R.string.position)}:",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        text = employee.unvoni ?: stringResource(id = R.string.not_given),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Row {
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .padding(bottom = 8.dp),
                        text = "${stringResource(id = R.string.room)}:",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        text = employee.xonasi ?: stringResource(id = R.string.not_given),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            IconButton(
                onClick = { intentReducer(HomeScreenClickIntents.CallEmployee(employee.phoneNo)) }) {
                Icon(imageVector = Icons.Default.Phone, contentDescription = null)
            }
            IconButton(
                modifier = Modifier.padding(end = 8.dp),
                onClick = { intentReducer(HomeScreenClickIntents.CreateCommand(employee.id)) }) {
                Image(
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun EmployeeItemPreview() {
    EmployeeManagementTheme {
        EmployeeItem(
            intentReducer = {},
            employee = Result(
                email = "anorov@gmail.com",
                username = "Hasan",
                lastName = "Anorov",
                id = 1,
                image = "",
                phoneNo = "",
                unvoni = "Senior Dev",
                isStaff = false,
                xonasi = "12-room"
            )
        )
    }
}

@Preview
@Composable
fun EmployeeItemPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        EmployeeItem(
            intentReducer = {},
            employee = Result(
                email = "anorov@gmail.com",
                username = "Hasan",
                lastName = "Anorov",
                id = 1,
                image = "",
                phoneNo = "",
                unvoni = "Senior Dev",
                isStaff = false,
                xonasi = "240-room"
            )
        )
    }
}