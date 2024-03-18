package com.ierusalem.employeemanagement.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel(),
    NavigationEventDelegate<HomeScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    private fun logoutUser(){
        viewModelScope.launch {
            repo.logoutUser().let {response ->
                if (response.isSuccessful){
                    repo.deleteToken()
                    repo.deleteRefreshToken()
                    emitNavigation(HomeScreenNavigation.NavigateToLogin)
                }else{
                    emitNavigation(HomeScreenNavigation.FailedToLogout)
                }
            }
        }
    }

    fun handleClickIntents(intent: HomeScreenClickIntents) {
        when (intent) {
            HomeScreenClickIntents.LogoutClick -> {
                logoutUser()
            }
            HomeScreenClickIntents.FabButtonClick -> {
                emitNavigation(HomeScreenNavigation.NavigateToCompose)
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
    val tabItems: List<String> = listOf("Buyruqlar", "Xodimlar"),
    val selectedTabIndex: Int = 0,
    val username: String = ""
)