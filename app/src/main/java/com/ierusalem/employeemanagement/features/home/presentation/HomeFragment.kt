package com.ierusalem.employeemanagement.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.components.EmployeeManagementDrawer
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

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
                    // Open drawer and reset state in VM.
                    LaunchedEffect(Unit) {
                        // wrap in try-finally to handle interruption whiles opening drawer
                        try {
                            drawerState.open()
                        } finally {
                            viewModel.resetOpenDrawerAction()
                        }
                    }
                }

                // Intercepts back navigation when the drawer is open
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
                    drawerState = drawerState,
                    onProfileClicked = {
                        scope.launch {
                            drawerState.close()
                            findNavController().navigate(R.id.profileFragment)
                        }
                    }
                ) {
                    EmployeeManagementTheme {
                        HomeScreen(
                            state = state,
                            onDrawerClick = {
                                viewModel.openDrawer()
                            }
                        )
                    }
                }
            }
        }
    }

}