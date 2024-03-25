package com.ierusalem.employeemanagement.features.settings

sealed class SettingsScreenEvents {
    data object NavIconClicked: SettingsScreenEvents()
    data object SystemLanClick: SettingsScreenEvents()
    data object EnglishLanClick: SettingsScreenEvents()
    data object RussianLanClick: SettingsScreenEvents()
    data object UzbLanClick: SettingsScreenEvents()
    data object SystemThemeClick: SettingsScreenEvents()
    data object LightThemeClick: SettingsScreenEvents()
    data object DarkThemeClick: SettingsScreenEvents()
}