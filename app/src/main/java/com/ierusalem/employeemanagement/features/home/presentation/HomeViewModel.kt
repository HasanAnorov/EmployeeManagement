package com.ierusalem.employeemanagement.features.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.Result
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.utils.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel(),
    NavigationEventDelegate<HomeScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    init {
        getCommands()
        getEmployees()
    }

    private fun updateLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    private fun logoutUser() {
        viewModelScope.launch {
            repo.logoutUser().let { response ->
                if (response.isSuccessful) {
                    repo.deleteToken()
                    repo.deleteRefreshToken()
                    emitNavigation(HomeScreenNavigation.NavigateToLogin)
                } else {
                    emitNavigation(HomeScreenNavigation.FailedToLogout)
                }
            }
        }
    }

    private fun getCommands() {
        try {
            updateLoading(true)
            viewModelScope.launch {
                repo.getMessage().let { response ->
                    if (response.isSuccessful) {
                        updateLoading(false)
                        _state.update {
                            it.copy(
                                commands = response.body()?.results ?: listOf()
                            )
                        }
                    } else {
                        updateLoading(false)
                    }
                }
            }
        } catch (e: Exception) {
            updateLoading(false)
            Log.d("ahi3646", "getCommands: catch fail - ${e.localizedMessage}")
        }
    }

    private fun getEmployees() {
        try {
            viewModelScope.launch {
                repo.getEmployees().let { response ->
                    if (response.isSuccessful) {
                        _state.update {
                            it.copy(
                                employees = response.body()?.results ?: listOf()
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            emitNavigation(HomeScreenNavigation.FailedToLoadEmployees)
        }
    }

    fun handleClickIntents(intent: HomeScreenClickIntents) {
        when (intent) {
            HomeScreenClickIntents.LogoutClick -> {
                logoutUser()
            }

            is HomeScreenClickIntents.CreateCommand -> {
                emitNavigation(HomeScreenNavigation.NavigateToCompose(intent.userId))
            }

            HomeScreenClickIntents.OnPullToRefreshCommands -> {
                getCommands()
            }

            is HomeScreenClickIntents.TabItemClick -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = intent.tabIndex
                    )
                }
            }
        }
    }

}

data class HomeScreenState(
    val tabItems: List<UiText> = listOf(
        UiText.StringResource(resId = R.string.commands),
        UiText.StringResource(resId = R.string.employees)
    ),
    val selectedTabIndex: Int = 0,
    val username: String = "",
    val isLoading: Boolean = false,
    val commands: List<Result> = listOf(),
    val employees: List<com.ierusalem.employeemanagement.features.home.presentation.employees.model.Result> = listOf(),
)
