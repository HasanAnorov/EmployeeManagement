package com.ierusalem.employeemanagement.features.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ierusalem.employeemanagement.app.EmployeeManagementApp
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel(),
    NavigationEventDelegate<LoginNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onUsernameChanged(userName: String) {
        _state.update {
            it.copy(userName = userName)
        }
    }

    fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            Log.d("ahi3646", "onLoginClick:  ${state.value.userName}  -  ${state.value.password}")
            authRepository.loginUser(
                username = state.value.userName,
                password = state.value.password
            ).let { response ->
                if (response.isSuccessful) {
                    emitNavigation(LoginNavigation.NavigateToMain)
                } else {
                    emitNavigation(LoginNavigation.InvalidResponse)
                }
            }
        }
    }

    // Define ViewModel factory in a companion object
//    companion object {
//
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                // Get the Application object from extras
//                val application = checkNotNull(extras[APPLICATION_KEY])
//
//                return LoginViewModel(
//                    (application as EmployeeManagementApp).authContainer.authRepository
//                ) as T
//            }
//        }
//    }

}

data class LoginScreenState(
    val userName: String = "",
    val password: String = "",
)