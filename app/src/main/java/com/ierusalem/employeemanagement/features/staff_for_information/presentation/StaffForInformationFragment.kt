package com.ierusalem.employeemanagement.features.staff_for_information.presentation

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
import com.ierusalem.employeemanagement.features.staff_for_information.domain.StaffForInformationNavigation
import com.ierusalem.employeemanagement.features.staff_for_information.domain.StaffForInformationViewmodel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class StaffForInformationFragment : Fragment() {

    private val viewModel: StaffForInformationViewmodel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getReceivedInformation()
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
                    StaffForInformationScreen(
                        state = state,
                        intentReducer = {
                            viewModel.handleClickIntents(it)
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

    private fun executeNavigation(event: StaffForInformationNavigation){
        when(event){
            StaffForInformationNavigation.ArrowBackClick -> {
                findNavController().popBackStack()
            }

            StaffForInformationNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}