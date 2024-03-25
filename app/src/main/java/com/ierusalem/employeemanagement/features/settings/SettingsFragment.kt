package com.ierusalem.employeemanagement.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                EmployeeManagementTheme {
                    SettingsScreen(intentReducer = { handleEvents(it) })
                }
            }
        }
    }

    private fun handleEvents(event: SettingsScreenEvents) {
        when (event) {
            SettingsScreenEvents.SystemLanClick -> {}
            SettingsScreenEvents.EnglishLanClick -> {}
            SettingsScreenEvents.RussianLanClick -> {}
            SettingsScreenEvents.UzbLanClick -> {}
            SettingsScreenEvents.SystemThemeClick -> {}
            SettingsScreenEvents.DarkThemeClick -> {}
            SettingsScreenEvents.LightThemeClick -> {}
            SettingsScreenEvents.NavIconClicked -> findNavController().popBackStack()
        }
    }

}