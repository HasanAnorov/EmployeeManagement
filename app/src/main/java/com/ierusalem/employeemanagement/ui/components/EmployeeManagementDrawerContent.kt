package com.ierusalem.employeemanagement.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenState
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EmployeeManagementDrawerContent(
    onProfileClicked: (String) -> Unit,
    onSettingsClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
    onPrivateJobClicked: () -> Unit,
    onStatisticsClicked: () -> Unit,
    state: HomeScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        DrawerHeader(
            username = "${state.username} ${state.lastName}",
            imageUrl = state.imageUrl,
            email = state.email
        )
        DividerItem()
        ProfileItem(
            onProfileClicked = { onProfileClicked("user_id") }
        )
        PrivateJobsItem {
            onPrivateJobClicked()
        }
        if (state.isSuperUser) {
            StatisticsItem {
                onStatisticsClicked()
            }
        }
        SettingsItem {
            onSettingsClicked()
        }
        LogoutItem {
            onLogoutClicked()
        }
    }
}

@Composable
private fun DrawerHeader(username: String, imageUrl: String, email: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 16.dp),
    ) {
        GlideImage(
            failure = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            imageModel = {
                imageUrl
            },
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            imageOptions = ImageOptions(
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = username,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = email,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun ProfileItem(
    onProfileClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onProfileClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(start = 8.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            painter = painterResource(id = R.drawable.baseline_account_circle_24),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.profile),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
private fun PrivateJobsItem(
    onPrivateJobClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onPrivateJobClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(start = 8.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            painter = painterResource(id = R.drawable.work),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.private_jobs),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
private fun StatisticsItem(
    onStatisticsClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onStatisticsClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(start = 8.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            painter = painterResource(id = R.drawable.statistic),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.statistics),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
private fun LogoutItem(
    onLogoutClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onLogoutClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(start = 8.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.logout),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
private fun SettingsItem(
    onSettingsClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onSettingsClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(start = 8.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            imageVector = Icons.Default.Settings,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun DividerItem(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}

@Composable
@Preview
fun DrawerPreview() {
    EmployeeManagementTheme {
        Surface {
            Column {
                EmployeeManagementDrawerContent(
                    state = HomeScreenState(true),
                    onProfileClicked = {},
                    onLogoutClicked = {},
                    onSettingsClicked = {},
                    onPrivateJobClicked = {},
                    onStatisticsClicked = {}
                )
            }
        }
    }
}

@Composable
@Preview
fun DrawerPreviewDark() {
    EmployeeManagementTheme(darkTheme = true) {
        Surface {
            Column {
                EmployeeManagementDrawerContent(
                    state = HomeScreenState(true),
                    onProfileClicked = {},
                    onLogoutClicked = {},
                    onSettingsClicked = {},
                    onPrivateJobClicked = {},
                    onStatisticsClicked = {}
                )
            }
        }
    }
}