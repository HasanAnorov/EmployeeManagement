package com.ierusalem.employeemanagement.features.home.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.MainActivity
import com.ierusalem.employeemanagement.ui.components.EmployeeManagementDrawer
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        if (requireActivity().intent.hasExtra(Constants.NOTIFICATION)) {
            Log.d("ahi3646", "onStart: ")
        }
        (activity as MainActivity).addOnNewIntentListener {
            Log.d("ahi3646", "noStart: ${it.data} ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission()
        (activity as MainActivity).addOnNewIntentListener {
            Log.d("ahi3646", "onCreate: ${it.data} ")
        }
    }

    private fun requestNotificationPermission() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if(!hasPermission) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val isFromCommand = arguments?.getBoolean(Constants.COMPOSE_COMMAND) ?: false
        if(isFromCommand){
            viewModel.changeSelectedTabIndex(5)
            //one request is enough, backend dev said other automatically will be refreshed
            viewModel.getCommands("yuborildi")
        }

        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val drawerOpen by viewModel.drawerShouldBeOpened.collectAsStateWithLifecycle()
                val scope = rememberCoroutineScope()
                if (drawerOpen) {
                    LaunchedEffect(Unit) {
                        try {
                            drawerState.open()
                        } finally {
                            viewModel.resetOpenDrawerAction()
                        }
                    }
                }

                if (drawerState.isOpen) {
                    BackHandler {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                }

                Log.d("ahi3646", "onCreateView: ${state.isDarkTheme} ")
                EmployeeManagementTheme(
                    darkTheme = state.isDarkTheme,
                    content = {
                        EmployeeManagementDrawer(
                            username = "${state.username} ${state.lastName}",
                            imageUrl = state.imageUrl,
                            email = state.email,
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
                            },
                            content = {
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
                        )
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

    private fun executeNavigation(navigation: HomeScreenNavigation) {
        when (navigation) {
            is HomeScreenNavigation.OnCreateCommands -> {
                val listString = Gson().toJson(Users(navigation.users))
                val bundle = bundleOf(Constants.USERS_TO_COMMAND to listString)
                findNavController().navigate(R.id.action_homeFragment_to_composeFragment, bundle)
            }
            HomeScreenNavigation.InvalidResponse ->{
                Toast.makeText(requireContext(), resources.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
            is HomeScreenNavigation.OnItemClick -> {
                val bundle = Bundle()
                bundle.putString(Constants.WORK_DESCRIPTION_KEY, navigation.workId)
                bundle.putBoolean(Constants.WORK_DESCRIPTION_KEY_FROM_HOME, true)
                findNavController().navigate(
                    R.id.action_homeFragment_to_workDescriptionFragment,
                    bundle
                )
            }

            is HomeScreenNavigation.CallEmployee -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:${navigation.phoneNumber}"))
                startActivity(intent)
            }

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
                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_t_logout), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}

data class Users(
    val users: List<String>
)