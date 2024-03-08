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
import androidx.fragment.app.viewModels
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.executeWithLifecycle

class LoginFragment : Fragment() {

    private val viewModel:LoginViewModel by viewModels<LoginViewModel>()

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
                            viewModel.onLoginClick()
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
                    "Muammo yuzaga keldi. Iltimos keyinroq urinib ko'ring",
                    Toast.LENGTH_SHORT
                ).show()
            }

            LoginNavigation.NavigateToMain -> {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                //findNavController().navigate(R.id.action_loginChooserFragment_to_basicAuthFragment)
            }
        }
    }

}