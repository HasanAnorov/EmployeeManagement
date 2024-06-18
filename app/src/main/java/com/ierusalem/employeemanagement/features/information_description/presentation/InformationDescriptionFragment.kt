package com.ierusalem.employeemanagement.features.information_description.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.core.downloader.AndroidDownloader
import com.ierusalem.employeemanagement.core.utils.Constants
import com.ierusalem.employeemanagement.core.utils.executeWithLifecycle
import com.ierusalem.employeemanagement.features.information_description.domain.InformationDescriptionViewmodel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class InformationDescriptionFragment : Fragment() {

    private var isSent by Delegates.notNull<Boolean>()
    private val viewModel: InformationDescriptionViewmodel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        isSent = arguments?.getBoolean(Constants.IS_SENT_FOR_INFORMATION) ?: false
        val id = arguments?.getInt(Constants.ID_FOR_INFORMATION_DESCRIPTION) ?: -1
        viewModel.getDescription(isSent, id.toString())
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
                    InformationDescScreen(
                        isSent = isSent,
                        state = state,
                        onArrowBackClick = {
                            findNavController().popBackStack()
                        },
                        onDownloadFile = {
                            val downloader = AndroidDownloader(requireContext())
                            downloader.downloadFile(it)
                        },
                        onDeleteInformationClicked = {id ->
                            viewModel.deleteInformation(id.toString())
                        },
                        onEditInformationClicked = {
                            findNavController().navigate(R.id.action_informationDescriptionFragment_to_forInformationEditFragment)
                        }
                    )
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenNavigation.executeWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            action = ::executeNavigation
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun executeNavigation(navigation: InformationDescriptionNavigation) {
        when (navigation) {
            InformationDescriptionNavigation.Failure -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
            InformationDescriptionNavigation.SuccessOnInformationDeletion -> {
                val bundle = Bundle()
                bundle.putBoolean(Constants.INFORMATION_SHOULD_REFRESH, true)
                findNavController().navigate(
                    R.id.action_informationDescriptionFragment_to_forInformationFragment,
                    bundle
                )
                Toast.makeText(
                    requireContext(),
                    getString(R.string.information_deleted_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}