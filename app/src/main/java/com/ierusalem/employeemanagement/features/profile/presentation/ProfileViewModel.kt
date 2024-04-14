package com.ierusalem.employeemanagement.features.profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.profile.data.NewPasswordRequest
import com.ierusalem.employeemanagement.features.profile.data.model.ProfileResponse
import com.ierusalem.employeemanagement.features.profile.domain.ProfileRepository
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val repo: ProfileRepository) : ViewModel(),
    NavigationEventDelegate<ProfileNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState()
    )
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(ProfileNavigation.Failure)
    }


    fun setPassword(oldPassword: String, newPassword: String){
        try {
            viewModelScope.launch(handler) {
                repo.setPassword(NewPasswordRequest(oldPassword, newPassword)).let {
                    if(it.isSuccessful){
                        emitNavigation(ProfileNavigation.PasswordChanged)
                    }else{
                        emitNavigation(ProfileNavigation.InvalidResponse)
                    }
                }
            }
        }catch (e: Exception){
            emitNavigation(ProfileNavigation.InvalidResponse)
        }
    }

    fun getUser() {
        try {
            viewModelScope.launch(handler) {
                repo.getUser().let { response ->
                    if(response.isSuccessful){
                        _state.update {
                            it.copy(
                                profileScreen = ProfileScreen.Success(
                                    content = response.body()!!
                                )
                            )
                        }
                    }else{
                        _state.update {
                            it.copy(
                                profileScreen = ProfileScreen.Error("Got invalid response!")
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    profileScreen = ProfileScreen.Error(e.localizedMessage ?: "Unknown error!")
                )
            }
        }
    }

}

sealed interface ProfileScreen {

    data object Loading : ProfileScreen

    data class Success(val content: ProfileResponse) : ProfileScreen

    data class Error(val message: String) : ProfileScreen

}

data class ProfileScreenState(
    val profileScreen: ProfileScreen = ProfileScreen.Loading
)
