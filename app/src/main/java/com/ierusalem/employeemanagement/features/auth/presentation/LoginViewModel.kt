package com.ierusalem.employeemanagement.features.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.core.ValidationResult
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel(),
    NavigationEventDelegate<LoginNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onUsernameChanged(userName: String) {
        _state.update {
            it.copy(username = userName)
        }
    }

    fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun saveUser(user: User){
        authRepository.saveAuthenticatedUser(user)
    }

    private fun saveToken(token: String){
        authRepository.saveToken(token)
    }

    fun loginIfFieldsAreValid(){
        val username = validateUsername()
        val password = validatePassword()
        if(!username.successful){
            _state.update {
                it.copy(
                    usernameError = username.errorMessage,
                )
            }
        }else{
            _state.update {
                it.copy(
                    usernameError = null
                )
            }
        }
        if(!password.successful){
            _state.update {
                it.copy(
                    passwordError = password.errorMessage,
                )
            }
        }else{
            _state.update {
                it.copy(
                    passwordError = null
                )
            }
        }
        if(username.successful && password.successful){
            viewModelScope.launch {
                authRepository.loginUser(
                    username = state.value.username,
                    password = state.value.password
                ).let { response ->
                    if (response.isSuccessful) {
                        Log.d("ahi3646_response", "onLoginClick: ${response.body()} ")
                        saveUser(response.body()!!.user)
                        saveToken(response.body()!!.access)

                        emitNavigation(LoginNavigation.NavigateToMain)
                    } else {
                        emitNavigation(LoginNavigation.InvalidResponse)
                    }
                }
            }
        }
    }

    private fun validateUsername(): ValidationResult {
        return when {
            state.value.username.isBlank() -> ValidationResult(
                successful = false,
                errorMessage = "Username can't be blank"
            )
            state.value.username.length < 3 -> ValidationResult(
                successful = false,
                errorMessage = "Username should be than 3 words!"
            )

            else -> ValidationResult(
                successful = true,
            )
        }
    }

    private fun validatePassword(): ValidationResult {
        return when {
            state.value.password.isBlank() -> ValidationResult(
                successful = false,
                errorMessage = "Password can't be blank"
            )
            state.value.username.length < 3 -> ValidationResult(
                successful = false,
                errorMessage = "Password should be than 3 words!"
            )

            else -> ValidationResult(
                successful = true,
            )
        }
    }

}

data class LoginScreenState(
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)