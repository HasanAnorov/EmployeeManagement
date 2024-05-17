package com.ierusalem.employeemanagement.features.personal_statistics.presentation

import android.R.attr.password
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Browser
import android.util.Base64
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
import com.ierusalem.employeemanagement.features.personal_statistics.domain.PersonalStatisticsViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel


class PersonalStatisticsFragment : Fragment() {

    private val viewModel: PersonalStatisticsViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getPersonalStatisticsSent()
        viewModel.getPersonalStatisticsReceived()
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
        val pref = PreferenceHelper(requireContext())
        when (navigation) {
            PersonalStatisticsNavigation.Failure -> {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }

            PersonalStatisticsNavigation.DownloadPersonalStatisticsReceived -> {
                Log.d("ahi3646", "executeNavigation: ${pref.getToken()} ")
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(Constants.PERSONAL_STATISTICS_DOWNLOAD_URL_RECEIVED))

                val authorization: String = "xamrayev@gmail.com" + ":" + "123"
                val authorizationBase64: String =
                    Base64.encodeToString(authorization.toByteArray(), 0)

                val bundle = Bundle()
//                bundle.putString("Authorization", "Basic $authorizationBase64")
                bundle.putString("Authorization :", pref.getToken())
                i.putExtra(Browser.EXTRA_HEADERS, bundle)
                Log.d("ahi3646", "executeNavigation Intent: $i ${i.extras} ")
                startActivity(i)
            }

            PersonalStatisticsNavigation.DownloadPersonalStatisticsSent -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(Constants.PERSONAL_STATISTICS_DOWNLOAD_URL_SENT))
                startActivity(i)
            }

            PersonalStatisticsNavigation.OnNavIconClick -> {
                findNavController().popBackStack()
            }
        }
    }

}