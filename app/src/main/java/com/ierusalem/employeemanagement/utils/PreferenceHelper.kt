package com.ierusalem.employeemanagement.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User

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

    fun saveAuthenticatedUser(user: User){
        val userGson = Gson().toJson(user)
        with(sharedPref.edit()) {
            putString(Constants.USERNAME_KEY, userGson)
            apply()
        }
    }

    fun getUser(): User{
        val myUserGson = sharedPref.getString(Constants.USERNAME_KEY, "")
        return Gson().fromJson(myUserGson, User::class.java)
    }

    fun getToken(): String {
        return sharedPref.getString(Constants.TOKEN_KEY, "")!!
    }
}