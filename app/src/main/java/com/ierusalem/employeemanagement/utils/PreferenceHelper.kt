package com.ierusalem.employeemanagement.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import com.google.gson.Gson
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User
import java.util.Locale

class PreferenceHelper(private val context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

    private fun Context.isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }

    fun saveToken(token: String) {
        with(sharedPref.edit()) {
            putString(Constants.TOKEN_KEY, "Bearer $token")
            apply()
        }
    }

    fun saveTheme(darkTheme: Boolean) {
        with(sharedPref.edit()) {
            putBoolean(Constants.LOCALE_THEME, darkTheme)
            apply()
        }
    }

    fun getTheme(): Boolean {
        val default = context.isDarkThemeOn()
        return sharedPref.getBoolean(Constants.LOCALE_THEME, default)
    }

    fun saveLocal(locale: String) {
        with(sharedPref.edit()) {
            putString(Constants.LOCALE_LANGUAGE, locale)
            apply()
        }
    }

    fun getLocal(): String {
        val default = Locale.getDefault().language
        return sharedPref.getString(Constants.LOCALE_LANGUAGE, default) ?: default
    }

    fun saveRefreshToken(token: String) {
        with(sharedPref.edit()) {
            putString(Constants.REFRESH_TOKEN_KEY, token)
            apply()
        }
    }

    fun deleteToken() {
        with(sharedPref.edit()) {
            remove(Constants.TOKEN_KEY)
            apply()
        }
    }

    fun deleteRefreshToken() {
        with(sharedPref.edit()) {
            remove(Constants.REFRESH_TOKEN_KEY)
            apply()
        }
    }

    fun isLogged(): Boolean {
        return getToken().isNotEmpty()
    }

    fun saveAuthenticatedUser(user: User) {
        val userGson = Gson().toJson(user)
        with(sharedPref.edit()) {
            putString(Constants.USERNAME_KEY, userGson)
            apply()
        }
    }

    fun getUser(): User {
        val myUserGson = sharedPref.getString(Constants.USERNAME_KEY, "")
        return Gson().fromJson(myUserGson, User::class.java)
    }

    fun getToken(): String {
        return sharedPref.getString(Constants.TOKEN_KEY, "")!!
    }

    fun getRefreshToken(): String {
        return sharedPref.getString(Constants.REFRESH_TOKEN_KEY, "")!!
    }
}