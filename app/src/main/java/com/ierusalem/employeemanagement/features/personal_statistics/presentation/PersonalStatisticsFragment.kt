package com.ierusalem.employeemanagement.features.personal_statistics.presentation

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
import com.ierusalem.employeemanagement.features.personal_statistics.domain.PersonalStatisticsViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalStatisticsFragment : Fragment() {

    private val viewModel: PersonalStatisticsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val state by viewModel.state.collectAsStateWithLifecycle()

                EmployeeManagementTheme {
                    PersonalStatisticsScreen(
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

    private fun executeNavigation(navigation: PersonalStatisticsNavigation) {
        when (navigation) {
            PersonalStatisticsNavigation.Failure -> {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }

            PersonalStatisticsNavigation.OnNavIconClick -> {
                findNavController().popBackStack()
            }
        }
    }

}