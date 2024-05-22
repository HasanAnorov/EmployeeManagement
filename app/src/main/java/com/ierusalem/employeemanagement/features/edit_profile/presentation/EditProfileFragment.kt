package com.ierusalem.employeemanagement.features.edit_profile.presentation

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class EditProfileFragment : Fragment() {

    private val viewModel: EditProfileViewModel by viewModel()

    private lateinit var username: String
    private lateinit var lastname: String
    private lateinit var patronymicName: String
    private lateinit var room: String
    private lateinit var position: String
    private lateinit var phoneNumber: String
    private lateinit var email: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        username = arguments?.getString(Constants.TO_EDIT_PROFILE_USERNAME) ?: ""
        lastname = arguments?.getString(Constants.TO_EDIT_PROFILE_LASTNAME) ?: ""
        patronymicName = arguments?.getString(Constants.TO_EDIT_PROFILE_PATRONYMIC) ?: ""
        room = arguments?.getString(Constants.TO_EDIT_PROFILE_ROOM) ?: ""
        position = arguments?.getString(Constants.TO_EDIT_PROFILE_POSITION) ?: ""
        email = arguments?.getString(Constants.TO_EDIT_PROFILE_EMAIL) ?: ""
        phoneNumber = arguments?.getString(Constants.TO_EDIT_PROFILE_PHONE_NUMBER) ?: ""

        viewModel.onEmailChanged(email)
        viewModel.onPositionChanged(position)
        viewModel.onPhoneNumberChanged(phoneNumber)
        viewModel.onLastnameChanged(lastname)
        viewModel.onPatronymicNameChanged(patronymicName)
        viewModel.onUsernameChanged(username)
        viewModel.onRoomChanged(room)
    }

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
                        },
                        onPatronymicChanged = {
                            viewModel.onPatronymicNameChanged(it)
                        },
                        onSaveClicked = {
                            val requestBodyBuilder = MultipartBody.Builder()
                            requestBodyBuilder.setType(MultipartBody.FORM)
                            requestBodyBuilder.addFormDataPart(
                                "email",
                                if (email != viewModel.state.value.newEmail) viewModel.state.value.newEmail else email
                            )
                            requestBodyBuilder.addFormDataPart(
                                "last_name",
                                if (lastname != viewModel.state.value.newLastname) viewModel.state.value.newLastname else lastname
                            )
                            requestBodyBuilder.addFormDataPart(
                                "patronymic_name",
                                if (patronymicName != viewModel.state.value.newPatronymicName) viewModel.state.value.newPatronymicName else patronymicName
                            )
                            requestBodyBuilder.addFormDataPart(
                                "username",
                                if (username != viewModel.state.value.newUsername) viewModel.state.value.newUsername else username
                            )
                            requestBodyBuilder.addFormDataPart(
                                "phone_no",
                                if (phoneNumber != viewModel.state.value.newPhoneNumber) viewModel.state.value.newPhoneNumber else phoneNumber
                            )
                            requestBodyBuilder.addFormDataPart(
                                "unvoni",
                                if (position != viewModel.state.value.newPosition) viewModel.state.value.newPosition else position
                            )
                            requestBodyBuilder.addFormDataPart(
                                "xonasi",
                                if (room != viewModel.state.value.newRoom) viewModel.state.value.newRoom else room
                            )
                            if (state.value.imageUri != null) {
                                val inputStream =
                                    requireContext().contentResolver.openInputStream(state.value.imageUri!!)
                                val imageFile = File.createTempFile(
                                    "image",
                                    ".jpg",
                                    requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                                )
                                val fileOutputStream = FileOutputStream(imageFile)
                                inputStream?.copyTo(fileOutputStream)
                                fileOutputStream.close()
                                requestBodyBuilder.addFormDataPart(
                                    "image",
                                    imageFile.name,
                                    imageFile.asRequestBody(
                                        "image/*".toMediaType()
                                    )
                                )
                            }
                            val requestBody = requestBodyBuilder.build()
                            viewModel.updateProfile(requestBody)
                        },
                        onNavigationIconClicked = {
                            findNavController().popBackStack()
                        }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenNavigation.executeWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            action = ::executeNavigation
        )
    }

    private fun executeNavigation(navigation: EditProfileNavigation) {
        when (navigation) {
            EditProfileNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.data_not_saved),
                    Toast.LENGTH_SHORT
                ).show()
            }

            EditProfileNavigation.Failure -> {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }

            EditProfileNavigation.NavigateToMain -> {
                val bundle = Bundle()
                bundle.putBoolean(Constants.PROFILE_CHANGE, true)
                findNavController().navigate(
                    R.id.action_editProfileFragment_to_profileFragment,
                    bundle
                )
            }
        }
    }

}