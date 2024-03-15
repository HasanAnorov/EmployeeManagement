package com.ierusalem.employeemanagement.features.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel:LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()
                EmployeeManagementTheme {
                    LoginScreen(
                        state = state,
                        onUsernameChanged = {
                            viewModel.onUsernameChanged(it)
                        },
                        onPasswordChanged = {
                            viewModel.onPasswordChanged(it)
                        },
                        onLoginClick = {
                            viewModel.loginIfFieldsAreValid()
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

    private fun executeNavigation(navigation: LoginNavigation) {
        when (navigation) {
            LoginNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    "Foydalanuvchi topilmadi",
                    Toast.LENGTH_SHORT
                ).show()
            }

            LoginNavigation.NavigateToMain -> {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

}