package com.ierusalem.employeemanagement.features.statistics.presentation

import android.content.Intent
import android.net.Uri
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
import com.ierusalem.employeemanagement.features.statistics.domain.StatisticsScreenNavigation
import com.ierusalem.employeemanagement.features.statistics.domain.StatisticsViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel


class StatisticsFragment: Fragment() {

    private val viewModel: StatisticsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                EmployeeManagementTheme {
                    StatisticsScreen(
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

    private fun executeNavigation(navigation: StatisticsScreenNavigation) {
        when (navigation) {
            StatisticsScreenNavigation.DownloadStatistics -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(Constants.STATISTICS_DOWNLOAD_URL))
                startActivity(i)
            }

            StatisticsScreenNavigation.NavIconClick -> {
                findNavController().popBackStack()
            }

            StatisticsScreenNavigation.DownloadPersonalStatistics -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(Constants.PERSONAL_STATISTICS_DOWNLOAD_URL))
                startActivity(i)
            }

            StatisticsScreenNavigation.Failure -> {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}