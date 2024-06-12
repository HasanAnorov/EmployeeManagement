package com.ierusalem.employeemanagement.features.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.core.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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

    private fun saveRefreshToken(refresh: String){
        authRepository.saveRefreshToken(refresh)
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(LoginNavigation.InvalidResponse)
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
            try {
                viewModelScope.launch(handler) {
                    val token = FirebaseMessaging.getInstance().token.await()
                    
                    authRepository.loginUser(
                        username = state.value.username.trim(),
                        password = state.value.password.trim(),
                        token = token
                    ).let { response ->
                        if (response.isSuccessful) {
                            saveUser(response.body()!!.user)
                            saveToken(response.body()!!.access)
                            saveRefreshToken(response.body()!!.refresh)

                            if(response.body()!!.user.isStaff){
                                emitNavigation(LoginNavigation.NavigateToMain)
                            }else{
                                emitNavigation(LoginNavigation.NavigateToStaffMain)
                            }
                        } else {
                            emitNavigation(LoginNavigation.InvalidResponse)
                        }
                    }
                }
            }catch (e: Exception){
                emitNavigation(LoginNavigation.InvalidResponse)
            }
        }
    }

    private fun validateUsername(): ValidationResult {
        return when {
            state.value.username.isBlank() -> ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.username_can_t_be_blank
                )
            )
            state.value.username.length < 3 -> ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.username_should_be_than_3_words
                )
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
                errorMessage = UiText.StringResource(
                    resId = R.string.password_cannot_be_blank
                )
            )
            state.value.username.length < 3 -> ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    resId = R.string.password_cannot_be_less
                )
            )

            else -> ValidationResult(
                successful = true,
            )
        }
    }

}

data class LoginScreenState(
    val username: String = "",
    val usernameError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
)

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)