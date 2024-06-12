package com.ierusalem.employeemanagement.features.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import java.util.Locale

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val preferenceHelper = PreferenceHelper(requireContext())
        val currentLocale = preferenceHelper.getLocal()
        val locale = when(currentLocale){
            "en" -> "English"
            "ru" -> "Russian"
            "uz" -> "Uzbek"
            else -> "Unknow"
        }

        return ComposeView(requireContext()).apply {
            setContent {
                EmployeeManagementTheme {
                    SettingsScreen(
                        intentReducer = { handleEvents(it) },
                        currentLocale = locale
                    )
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun setAppLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = requireActivity().resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        activity?.recreate()
    }

    private fun handleEvents(event: SettingsScreenEvents) {
        val preferenceHelper = PreferenceHelper(requireContext())
        when (event) {
            SettingsScreenEvents.SystemLanClick -> {
                val locale = Locale.getDefault().language
                preferenceHelper.saveLocal(locale)
                setAppLocale(locale)
            }
            SettingsScreenEvents.EnglishLanClick -> {
                preferenceHelper.saveLocal("en")
                setAppLocale("en")
            }
            SettingsScreenEvents.RussianLanClick -> {
                preferenceHelper.saveLocal("ru")
                setAppLocale("ru")
            }
            SettingsScreenEvents.UzbLanClick -> {
                preferenceHelper.saveLocal("uz")
                setAppLocale("uz")
            }
            SettingsScreenEvents.SystemThemeClick -> {}
            SettingsScreenEvents.DarkThemeClick -> {}
            SettingsScreenEvents.LightThemeClick -> {}
            SettingsScreenEvents.NavIconClicked -> findNavController().popBackStack()
        }
    }

}