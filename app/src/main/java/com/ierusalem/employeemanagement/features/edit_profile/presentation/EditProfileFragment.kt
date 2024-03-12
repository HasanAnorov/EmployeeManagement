package com.ierusalem.employeemanagement.features.edit_profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment : Fragment() {

    private val viewModel: EditProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickVisualMedia(),
                    onResult = { uri ->
                        viewModel.updateImageUri(uri)
                    }
                )

                EmployeeManagementTheme {
                    val state = viewModel.state.collectAsStateWithLifecycle()
                    EditProfileScreen(
                        state = state.value,
                        onPhotoPickerClicked = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        onEmailChanged = {
                            viewModel.onEmailChanged(it)
                        },
                        onPositionChanged = {
                            viewModel.onPositionChanged(it)
                        },
                        onRoomChanged = {
                            viewModel.onRoomChanged(it)
                        },
                        onPhoneNumberChanged = {
                            viewModel.onPhoneNumberChanged(it)
                        },
                        onLastnameChanged = {
                            viewModel.onLastnameChanged(it)
                        },
                        onUsernameChanged = {
                            viewModel.onUsernameChanged(it)
                        }
                    )
                }
            }
        }
    }

}