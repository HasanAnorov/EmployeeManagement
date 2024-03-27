package com.ierusalem.employeemanagement.features.work_description.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkDescriptionFragment: Fragment() {

    private val viewModel: WorkDescriptionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
           setContent {
               val state by viewModel.state.collectAsStateWithLifecycle()
               EmployeeManagementTheme {
                   WorkDescriptionScreen(state = state)
               }
           }
        }
    }

}