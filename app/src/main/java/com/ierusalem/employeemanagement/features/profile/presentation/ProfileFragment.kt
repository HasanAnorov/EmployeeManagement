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
import com.ierusalem.employeemanagement.core.utils.Constants
import com.ierusalem.employeemanagement.core.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
                            bundle.putString(
                                Constants.TO_EDIT_PROFILE_USERNAME,
                                userData.user.username
                            )
                            bundle.putString(
                                Constants.TO_EDIT_PROFILE_LASTNAME,
                                userData.user.lastName
                            )
                            bundle.putString(
                                Constants.TO_EDIT_PROFILE_PATRONYMIC,
                                userData.user.patronymicName
                            )
                            bundle.putString(Constants.TO_EDIT_PROFILE_ROOM, userData.user.xonasi)
                            bundle.putString(
                                Constants.TO_EDIT_PROFILE_POSITION,
                                userData.user.unvoni
                            )
                            bundle.putString(
                                Constants.TO_EDIT_PROFILE_EMAIL, userData.user
                                    .email
                            )
                            bundle.putString(
                                Constants.TO_EDIT_PROFILE_PHONE_NUMBER,
                                userData.user.phoneNo
                            )
                            findNavController().navigate(
                                R.id.action_profileFragment_to_editProfileFragment,
                                bundle
                            )
                        },
                        onNavigationIconClicked = {
                            findNavController().popBackStack()
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
                    getString(R.string.password_didn_t_change),
                    Toast.LENGTH_SHORT
                ).show()
            }

            ProfileNavigation.Failure -> {
                Toast.makeText(requireContext(), resources.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }

            ProfileNavigation.PasswordChanged -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.password_has_changed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}