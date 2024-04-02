package com.ierusalem.employeemanagement.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User
import java.util.Locale

class PreferenceHelper (context: Context){
    private val sharedPref: SharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)



    fun saveToken(token:String){
        with(sharedPref.edit()) {
            putString(Constants.TOKEN_KEY, "Bearer $token")
            apply()
        }
    }

    fun saveLocal(locale:String){
        with(sharedPref.edit()) {
            putString(Constants.LOCALE_LANGUAGE, locale)
            apply()
        }
    }

    fun getLocal(): String{
        val default = Locale.getDefault().language
        return sharedPref.getString(Constants.LOCALE_LANGUAGE, default )  ?: default
    }

    fun saveRefreshToken(token:String){
        with(sharedPref.edit()) {
            putString(Constants.REFRESH_TOKEN_KEY, token)
            apply()
        }
    }

    fun deleteToken(){
        with(sharedPref.edit()) {
            remove(Constants.TOKEN_KEY)
            apply()
        }
    }

    fun deleteRefreshToken(){
        with(sharedPref.edit()) {
            remove(Constants.REFRESH_TOKEN_KEY)
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
        Log.d("ahi3646", "getToken: ${sharedPref.getString(Constants.TOKEN_KEY, "")!!}")
        return sharedPref.getString(Constants.TOKEN_KEY, "")!!
    }

    fun getRefreshToken(): String {
        return sharedPref.getString(Constants.REFRESH_TOKEN_KEY, "")!!
    }
}