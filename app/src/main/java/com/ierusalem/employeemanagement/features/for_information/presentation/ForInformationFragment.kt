package com.ierusalem.employeemanagement.features.for_information.presentation

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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.for_information.domain.ForInformationViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForInformationFragment : Fragment() {

    private val viewModel: ForInformationViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getReceivedInformationBadgeCount()
        viewModel.getReceivedInformation()
        viewModel.getSenInformation()
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
                    ForInformationScreen(
                        state = state,
                        intentReducer = {
                            viewModel.handleClickIntents(it)
                        },
                        onItemClick = {
                            val bundle = Bundle()
                            bundle.putInt(Constants.ID_FOR_INFORMATION_DESCRIPTION,it)
                            findNavController().navigate(R.id.action_forInformationFragment_to_informationDescriptionFragment, bundle)
                        },
                        onItemClickSent = {
                            val bundle = Bundle()
                            bundle.putBoolean(Constants.IS_SENT_FOR_INFORMATION, true)
                            bundle.putInt(Constants.ID_FOR_INFORMATION_DESCRIPTION,it)
                            findNavController().navigate(R.id.action_forInformationFragment_to_informationDescriptionFragment, bundle)
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

    private fun executeNavigation(event: ForInformationNavigation){
        when(event){
            ForInformationNavigation.ArrowBackClick -> {
                findNavController().popBackStack()
            }

            ForInformationNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}