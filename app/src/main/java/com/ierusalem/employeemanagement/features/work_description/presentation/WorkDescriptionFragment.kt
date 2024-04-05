package com.ierusalem.employeemanagement.features.work_description.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.ierusalem.employeemanagement.features.downloader.AndroidDownloader
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkDescriptionFragment : Fragment() {

    private val viewModel: WorkDescriptionViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val workId = arguments?.getString(Constants.WORK_DESCRIPTION_KEY)
        val isFromHome = arguments?.getBoolean(Constants.WORK_DESCRIPTION_KEY_FROM_HOME) ?: false
        if (isFromHome){
            viewModel.isFromHome(true)
        }
        if (workId!= null && !isFromHome){
            Log.d("ahi3646", "onAttach: user ")
            viewModel.getMessageById(workId)
        }
        if(isFromHome && workId != null){
            Log.d("ahi3646", "onAttach: admin ")
            viewModel.getMessageByIdAdmin(workId)
        }
        if (workId == null) {
            Toast.makeText(
                requireContext(),
                getString(R.string.something_went_wrong),
                Toast.LENGTH_SHORT
            ).show()
        }
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
                    WorkDescriptionScreen(
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

    private fun executeNavigation(navigation: WorkDescriptionNavigation) {
        when (navigation) {
            WorkDescriptionNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_t_get_work_description),
                    Toast.LENGTH_SHORT
                ).show()
            }

            WorkDescriptionNavigation.InvalidResponseMarkAsDone -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_t_mark_this_work_as_done),
                    Toast.LENGTH_SHORT
                ).show()
            }

            is WorkDescriptionNavigation.DownloadFile ->{
                val downloader = AndroidDownloader(requireContext())
                downloader.downloadFile(navigation.url)
            }

            WorkDescriptionNavigation.MarkedAsDone -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.work_marked_as_done), Toast.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }

            WorkDescriptionNavigation.NavIconClick -> {
                findNavController().popBackStack()
//                val bundle = Bundle()
//                bundle.putBoolean(Constants.FROM_WORK_DESCRIPTION, true)
//                findNavController().navigate(R.id.action_workDescriptionFragment_to_staffHomeFragment, bundle)
            }
        }
    }

}