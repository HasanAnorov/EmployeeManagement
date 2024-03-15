package com.ierusalem.employeemanagement.features.profile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //val isFromEditProfile = arguments?.getBoolean(Constants.PROFILE_CHANGE) ?: false
        //Log.d("ahi3646_profile", "onAttach: $isFromEditProfile ")
//        if (isFromEditProfile){
//            viewModel.getUser()
//        }
        viewModel.getUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                EmployeeManagementTheme {
                    ProfileScreen(
                        onPasswordChange = { old, new ->
                            viewModel.setPassword(old, new)
                        },
                        onEditProfileClick = { userData ->
                            val bundle = Bundle()
                            bundle.putString(Constants.TO_EDIT_PROFILE_USERNAME, userData.username)
                            bundle.putString(Constants.TO_EDIT_PROFILE_LASTNAME, userData.lastName)
                            bundle.putString(Constants.TO_EDIT_PROFILE_ROOM, userData.room)
                            bundle.putString(Constants.TO_EDIT_PROFILE_POSITION, userData.position)
                            bundle.putString(Constants.TO_EDIT_PROFILE_EMAIL, userData.email)
                            bundle.putString(
                                Constants.TO_EDIT_PROFILE_PHONE_NUMBER,
                                userData.phoneNumber
                            )
                            findNavController().navigate(
                                R.id.action_profileFragment_to_editProfileFragment,
                                bundle
                            )
                        },
                        state = state.profileScreen
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

    private fun executeNavigation(navigation: ProfileNavigation) {
        when (navigation) {
            ProfileNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    "Parolni o'zgartirilmadi",
                    Toast.LENGTH_SHORT
                ).show()
            }

            ProfileNavigation.PasswordChanged -> {
                Toast.makeText(
                    requireContext(),
                    "Parol o'zgartirildi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}