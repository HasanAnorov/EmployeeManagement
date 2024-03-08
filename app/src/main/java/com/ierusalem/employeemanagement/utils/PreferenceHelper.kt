package com.ierusalem.employeemanagement.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper (context: Context){
    private val sharedPref: SharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

    fun saveToken(token:String){
        with(sharedPref.edit()) {
            putString(Constants.TOKEN_KEY, "Bearer $token")
            apply()
        }
    }

    fun isLogged(): Boolean{
        return getToken().isNotEmpty()
    }

    fun saveAuthenticatedUser(username: String){
        with(sharedPref.edit()) {
            putString(Constants.USERNAME_KEY, username)
            apply()
        }
    }
    fun getToken(): String {
        return sharedPref.getString(Constants.TOKEN_KEY, "")!!
    }
}