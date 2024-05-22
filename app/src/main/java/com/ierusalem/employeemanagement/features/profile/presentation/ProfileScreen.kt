package com.ierusalem.employeemanagement.features.profile.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.profile.data.model.ProfileResponse
import com.ierusalem.employeemanagement.features.profile.data.model.User
import com.ierusalem.employeemanagement.ui.components.AnimatingFabContent
import com.ierusalem.employeemanagement.ui.components.CommonJetHubLoginButton
import com.ierusalem.employeemanagement.ui.components.CommonTopBar
import com.ierusalem.employeemanagement.ui.components.ErrorScreen
import com.ierusalem.employeemanagement.ui.components.LoadingScreen
import com.ierusalem.employeemanagement.ui.components.SimpleFilledTextField
import com.ierusalem.employeemanagement.ui.components.baselineHeight
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onEditProfileClick: (ProfileResponse) -> Unit,
    state: ProfileScreen,
    onPasswordChange: (String, String) -> Unit,
    nestedScrollInteropConnection: NestedScrollConnection = rememberNestedScrollInteropConnection(),
    onNavigationIconClicked: () -> Unit
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        CommonTopBar(
            containerColor = MaterialTheme.colorScheme.background,
            onNavIconPressed = { onNavigationIconClicked() },
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            title = {
                Text(
                    text = stringResource(id = R.string.profile),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            content = {
                when (state) {
                    ProfileScreen.Loading -> LoadingScreen()
                    is ProfileScreen.Success -> {
                        val scrollState = rememberScrollState()
                        val userData = state.content
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxWidth()
                                .nestedScroll(nestedScrollInteropConnection),
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
                                        UserInfoFields(
                                            onPasswordChange = { old, new ->
                                                onPasswordChange(old, new)
                                            },
                                            userData = userData,
                                            containerHeight = this@BoxWithConstraints.maxHeight
                                        )
                                    }
                                }

                                val fabExtended by remember { derivedStateOf { scrollState.value == 0 } }
                                ProfileFab(
                                    extended = fabExtended,
                                    userIsMe = true,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .offset(y = ((-100).dp)),
                                    onFabClicked = { onEditProfileClick(userData) }
                                )
                            }
                        )
                    }

                    is ProfileScreen.Error -> ErrorScreen()
                }
            }
        )

    }
}

@Composable
private fun UserInfoFields(
    onPasswordChange: (String, String) -> Unit,
    userData: ProfileResponse,
    containerHeight: Dp
) {
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        NameAndPosition(userData)
        ProfileProperty(
            stringResource(R.string.position),
            userData.user.unvoni ?: stringResource(R.string.not_given)
        )
        ProfileProperty(stringResource(R.string.phone_number), userData.user.phoneNo)
        ProfileProperty(
            stringResource(R.string.room),
            userData.user.xonasi ?: stringResource(id = R.string.not_given)
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        var oldPassword by remember { mutableStateOf(TextFieldValue("")) }
        var newPassword by remember { mutableStateOf(TextFieldValue("")) }
        SimpleFilledTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            leadingIcon = Icons.Default.Lock,
            label = stringResource(R.string.old_password),
            value = oldPassword.text,
            onValueChanged = { oldPassword = TextFieldValue(it) })
        SimpleFilledTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            leadingIcon = Icons.Default.Lock,
            label = stringResource(R.string.new_password),
            value = newPassword.text,
            onValueChanged = { newPassword = TextFieldValue(it) }
        )
        CommonJetHubLoginButton(
            onClick = {
                if (oldPassword.text.length > 2 && newPassword.text.length > 2) {
                    onPasswordChange(oldPassword.text, newPassword.text)
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.enter_at_leas_3_characters), Toast.LENGTH_SHORT
                    ).show()
                }
            },
            textStyle = MaterialTheme.typography.labelSmall,
            text = stringResource(R.string.change_password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.primary),
        )
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun NameAndPosition(
    userData: ProfileResponse
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
private fun Name(userData: ProfileResponse, modifier: Modifier = Modifier) {
    Text(
        text = "${userData.user.username} ${userData.user.lastName} ${userData.user.patronymicName}",
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun Position(userData: ProfileResponse, modifier: Modifier = Modifier) {
    Text(
        text = userData.user.email,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    data: ProfileResponse,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }
    Log.d("ahi3646", "ProfileHeader: ${Constants.BASE_URL}${data.user.image} ")
    GlideImage(
        failure = { painterResource(id = R.drawable.baseline_account_circle_24) },
        imageModel = { "${Constants.BASE_URL}${data.user.image}" },
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
    EmployeeManagementTheme(darkTheme = false) {
        ProfileScreen(
            onPasswordChange = { _, _ -> },
            onEditProfileClick = {},
            onNavigationIconClicked = {},
            state = ProfileScreen.Success(
                content = ProfileResponse(
                    status = 200,
                    user = User(
                        email = "nurbek@gmail.com",
                        lastName = "Karlo",
                        username = "Jonathan",
                        image = "R.drawable.baseline_account_circle_24",
                        phoneNo = "931234567",
                        unvoni = "Senior Dev",
                        xonasi = "240",
                        id = 1,
                        isStaff = true,
                        isSuperUser = false,
                        patronymicName = "Hasan"
                    )
                )
            )
        )
    }
}

@Preview
@Composable
fun ConvPreviewPortraitMeDefault() {
    EmployeeManagementTheme(darkTheme = true) {
        ProfileScreen(
            onEditProfileClick = {},
            onNavigationIconClicked = {},
            onPasswordChange = { _, _ -> },
            state = ProfileScreen.Success(
                content = ProfileResponse(
                    status = 200,
                    user = User(
                        email = "nurbek@gmail.com",
                        lastName = "Karlo",
                        username = "Jonathan",
                        image = "R.drawable.baseline_account_circle_24",
                        phoneNo = "931234567",
                        unvoni = "Senior Dev",
                        xonasi = "240",
                        id = 1,
                        isStaff = true,
                        isSuperUser = false,
                        patronymicName = "Hasan"
                    )
                )
            )
        )
    }
}
