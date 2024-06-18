package com.ierusalem.employeemanagement.features.information_description.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.core.downloader.AndroidDownloader
import com.ierusalem.employeemanagement.features.information_description.domain.InformationDescriptionViewmodel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.core.utils.Constants
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
                        onDeleteInformationClicked = {

                        },
                        onEditInformationClicked = {
                            findNavController().navigate(R.id.action_informationDescriptionFragment_to_forInformationEditFragment)
                        }
                    )
                }
            }
        }
    }

}