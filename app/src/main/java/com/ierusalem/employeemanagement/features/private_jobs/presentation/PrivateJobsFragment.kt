package com.ierusalem.employeemanagement.features.private_jobs.presentation

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
import com.ierusalem.employeemanagement.features.private_jobs.domain.PrivateJobsViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.core.utils.Constants
import com.ierusalem.employeemanagement.core.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class PrivateJobsFragment : Fragment() {

    private val viewModel: PrivateJobsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                EmployeeManagementTheme {
                    PrivateJobsScreen(
                        state = state,
                        intentReducer = {
                            viewModel.handleEvents(it)
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

    private fun executeNavigation(navigation: PrivateJobsNavigation) {
        when (navigation) {
            PrivateJobsNavigation.Failure -> {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
            PrivateJobsNavigation.NavIconClick ->{
                findNavController().popBackStack()
            }
            is PrivateJobsNavigation.OnItemClick -> {
                val bundle = Bundle()
                bundle.putString(Constants.WORK_DESCRIPTION_KEY, navigation.workId)
                // to enter work description as staff
//                bundle.putBoolean(Constants.WORK_DESCRIPTION_KEY_FROM_HOME, false)
                findNavController().navigate(
                    R.id.action_privateJobsFragment_to_workDescriptionFragment,
                    bundle
                )
            }
        }
    }

}