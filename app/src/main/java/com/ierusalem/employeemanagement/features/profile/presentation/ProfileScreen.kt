package com.ierusalem.employeemanagement.features.profile.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.components.AnimatingFabContent
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import com.ierusalem.employeemanagement.ui.components.baselineHeight
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit,
    state: ProfileScreen,
    nestedScrollInteropConnection: NestedScrollConnection = rememberNestedScrollInteropConnection()
) {
    when(state){
        ProfileScreen.Loading -> LoadingScreen()
        is ProfileScreen.Success -> {
            val scrollState = rememberScrollState()
            val userData = state.content
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollInteropConnection)
                    .systemBarsPadding(),
                content = {
                    Surface {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(scrollState),
                        ) {
                            ProfileHeader(
                                scrollState,
                                userData,
                                this@BoxWithConstraints.maxHeight
                            )
                            UserInfoFields(userData, this@BoxWithConstraints.maxHeight)
                        }
                    }

                    val fabExtended by remember { derivedStateOf { scrollState.value == 0 } }
                    ProfileFab(
                        extended = fabExtended,
                        userIsMe = userData.isMe(),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            // Offsets the FAB to compensate for CoordinatorLayout collapsing behaviour
                            .offset(y = ((-100).dp)),
                        onFabClicked = { onEditProfileClick() }
                    )
                }
            )
        }
        is ProfileScreen.Error -> ErrorScreen()
    }
}

@Composable
private fun UserInfoFields(userData: ProfileScreenData, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        NameAndPosition(userData)
        ProfileProperty(stringResource(R.string.position), userData.position ?: "position not given")
        ProfileProperty(stringResource(R.string.phone_number), userData.phoneNumber)
        ProfileProperty(stringResource(R.string.room), userData.room ?: "room not given")
        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun NameAndPosition(
    userData: ProfileScreenData
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Name(
            userData,
            modifier = Modifier.baselineHeight(32.dp)
        )
        Position(
            userData,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .baselineHeight(24.dp)
        )
    }
}

@Composable
private fun Name(userData: ProfileScreenData, modifier: Modifier = Modifier) {
    Text(
        text = "${userData.username} ${userData.lastName}",
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun Position(userData: ProfileScreenData, modifier: Modifier = Modifier) {
    Text(
        text = userData.email,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    data: ProfileScreenData,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    GlideImage(
        failure = { painterResource(id = R.drawable.baseline_account_circle_24) },
        imageModel = { "${Constants.BASE_URL}${data.image}" },
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            // TODO: Update to use offset to avoid recomposition
            .padding(
                start = 16.dp,
                top = offsetDp,
                end = 16.dp
            )
            .clip(CircleShape),
        imageOptions = ImageOptions(
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    )

}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        HorizontalDivider()
        Text(
            text = label,
            modifier = Modifier.baselineHeight(24.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        val style = if (isLink) {
            MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.typography.bodyLarge
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}

@Composable
fun ProfileFab(
    extended: Boolean,
    userIsMe: Boolean,
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit = { }
) {
    key(userIsMe) { // Prevent multiple invocations to execute during composition
        FloatingActionButton(
            onClick = onFabClicked,
            modifier = modifier
                .padding(16.dp)
                .navigationBarsPadding()
                .height(48.dp)
                .widthIn(min = 48.dp),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ) {
            AnimatingFabContent(
                icon = {
                    Icon(
                        imageVector = if (userIsMe) Icons.Outlined.Create else Icons.AutoMirrored.Outlined.Chat,
                        contentDescription = stringResource(
                            if (userIsMe) R.string.edit_profile else R.string.message
                        )
                    )
                },
                text = {
                    Text(
                        //todo - temporary solution
                        modifier = Modifier.padding(top = 14.dp),
                        text = stringResource(
                            id = if (userIsMe) R.string.edit_profile else R.string.message
                        ),
                    )
                },
                extended = extended
            )
        }
    }
}

@Preview
@Composable
fun ConvPreviewLandscapeMeDefault() {
    EmployeeManagementTheme {
        ProfileScreen(
            onEditProfileClick = {},
            state = ProfileScreen.Success(
                content = ProfileScreenData(
                    userId = 1,
                    email  ="nurbek@gmail.com",
                    lastName = "Karlo",
                    username = "Jonathan",
                    image = "R.drawable.baseline_account_circle_24",
                    phoneNumber = "931234567",
                    position = "Senior Dev",
                    room = "240"
                )
            )
        )
    }
}

@Preview
@Composable
fun ConvPreviewPortraitMeDefault() {
    EmployeeManagementTheme {
        ProfileScreen(
            onEditProfileClick = {},
            state = ProfileScreen.Success(
                content = ProfileScreenData(
                    userId = 1,
                    email  ="nurbek@gmail.com",
                    lastName = "Karlo",
                    username = "Jonathan",
                    image = "R.drawable.baseline_account_circle_24",
                    phoneNumber = "931234567",
                    position = "Senior Dev",
                    room = "240"
                )
            )
        )
    }
}
