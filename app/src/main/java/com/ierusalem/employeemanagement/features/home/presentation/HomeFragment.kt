package com.ierusalem.employeemanagement.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.components.EmployeeManagementDrawer
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val preferenceHelper = PreferenceHelper(requireContext())
        val user = preferenceHelper.getUser()

        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()
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

                EmployeeManagementDrawer(
                    username = "${user.username} ${user.lastName}",
                    imageUrl = user.image,
                    email = user.email,
                    drawerState = drawerState,
                    onProfileClicked = {
                        scope.launch {
                            drawerState.close()
                            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                        }
                    },
                    onSettingsClicked = {
                        scope.launch {
                            drawerState.close()
                            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                        }
                    },
                    onLogoutClicked = {
                        scope.launch {
                            drawerState.close()
                            viewModel.handleClickIntents(HomeScreenClickIntents.LogoutClick)
                        }
                    }
                ) {
                    EmployeeManagementTheme {
                        HomeScreen(
                            state = state,
                            onDrawerClick = {
                                viewModel.openDrawer()
                            },
                            intentReducer = {
                                viewModel.handleClickIntents(it)
                            }
                        )
                    }
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

    private fun executeNavigation(navigation: HomeScreenNavigation) {
        when (navigation) {
            HomeScreenNavigation.FailedToLoadEmployees -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.problem_on_loading_employees_list),
                    Toast.LENGTH_SHORT
                ).show()
            }

            is HomeScreenNavigation.NavigateToCompose -> {
                val bundle = Bundle()
                bundle.putString(Constants.COMPOSE_PROFILE_ID, navigation.userId.toString())
                findNavController().navigate(R.id.action_homeFragment_to_composeFragment, bundle)
            }

            HomeScreenNavigation.NavigateToLogin -> {
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }

            HomeScreenNavigation.FailedToLogout -> {
                Toast.makeText(requireContext(),
                    getString(R.string.can_t_logout), Toast.LENGTH_SHORT).show()
            }
        }
    }

}