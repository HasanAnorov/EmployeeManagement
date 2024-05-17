package com.ierusalem.employeemanagement.features.staff_home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.staff_home.domain.StaffHomeViewModel
import com.ierusalem.employeemanagement.ui.components.EmployeeManagementStaffDrawer
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StaffHomeFragment : Fragment() {

    private val viewModel: StaffHomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val isFromWorkDesc = arguments?.getBoolean(Constants.FROM_WORK_DESCRIPTION) ?: false
        Log.d("ahi3646", "onAttach: $isFromWorkDesc ")
        if (isFromWorkDesc) {
            viewModel.getUserMessages("yuborildi")
            viewModel.getUserMessages("qabulqildi")
            viewModel.getUserMessages("bajarildi")
            viewModel.getUserMessages("bajarilmadi")

            viewModel.getUserMessages("kechikibbajarildi")
        }

        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val drawerOpen by viewModel.drawerShouldBeOpened
                    .collectAsStateWithLifecycle()

                if (drawerOpen) {
                    LaunchedEffect(Unit) {
                        try {
                            drawerState.open()
                        } finally {
                            viewModel.resetOpenDrawerAction()
                        }
                    }
                }

                val scope = rememberCoroutineScope()
                if (drawerState.isOpen) {
                    BackHandler {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                }
                EmployeeManagementTheme(
                    darkTheme = state.isDarkTheme,
                    content = {
                        EmployeeManagementStaffDrawer(
                            username = "${state.username} ${state.lastName}",
                            imageUrl = state.imageUrl,
                            email = state.email,
                            drawerState = drawerState,
                            onProfileClicked = {
                                scope.launch {
                                    drawerState.close()
                                    findNavController().navigate(R.id.action_staffHomeFragment_to_profileFragment)
                                }
                            },
                            onSettingsClicked = {
                                scope.launch {
                                    drawerState.close()
                                    findNavController().navigate(R.id.action_staffHomeFragment_to_settingsFragment)
                                }
                            },
                            onLogoutClicked = {
                                scope.launch {
                                    drawerState.close()
                                    viewModel.handleEvents(StaffHomeScreenEvents.LogoutClick)
                                }
                            },
                            onForInformationClick = {
                                scope.launch {
                                    drawerState.close()
                                    findNavController().navigate(R.id.action_staffHomeFragment_to_staffForInformationFragment)
                                }
                            },
                            onPersonalStatsClicked = {
                                scope.launch {
                                    drawerState.close()
                                    findNavController().navigate(R.id.action_staffHomeFragment_to_personalStatisticsFragment)
                                }
                            }
                        ) {
                            StaffHomeScreen(
                                state = state,
                                onDrawerClick = {
                                    viewModel.openDrawer()
                                },
                                intentReducer = {
                                    viewModel.handleEvents(it)
                                }
                            )
                        }
                    }
                )
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

    private fun executeNavigation(navigation: StaffHomeScreenNavigation) {
        when (navigation) {
            StaffHomeScreenNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }

            StaffHomeScreenNavigation.NavigateToLogin -> {
                findNavController().navigate(R.id.action_staffHomeFragment_to_loginFragment)
            }

            StaffHomeScreenNavigation.FailedToLogout -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_t_logout), Toast.LENGTH_SHORT
                ).show()
            }

            is StaffHomeScreenNavigation.OnItemClick -> {
                val bundle = Bundle()
                bundle.putString(Constants.WORK_DESCRIPTION_KEY, navigation.workId)
                findNavController().navigate(
                    R.id.action_staffHomeFragment_to_workDescriptionFragment,
                    bundle
                )
            }
        }
    }

}