package com.ierusalem.employeemanagement.features.settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import java.util.Locale


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



    fun Context.setAppLocale(language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        return createConfigurationContext(config)
    }

    private fun handleEvents(event: SettingsScreenEvents) {
        when (event) {
            SettingsScreenEvents.SystemLanClick -> {
                val locale = Locale.getDefault().language
                requireContext().setAppLocale(locale)
            }
            SettingsScreenEvents.EnglishLanClick -> {
                requireContext().setAppLocale("en")
            }
            SettingsScreenEvents.RussianLanClick -> {
                requireContext().setAppLocale("ru")
            }
            SettingsScreenEvents.UzbLanClick -> {
                requireContext().setAppLocale("uz")
            }
            SettingsScreenEvents.SystemThemeClick -> {}
            SettingsScreenEvents.DarkThemeClick -> {}
            SettingsScreenEvents.LightThemeClick -> {}
            SettingsScreenEvents.NavIconClicked -> findNavController().popBackStack()
        }
    }

}