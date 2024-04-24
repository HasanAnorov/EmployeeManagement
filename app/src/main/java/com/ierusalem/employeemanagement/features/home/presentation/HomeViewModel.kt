package com.ierusalem.employeemanagement.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.Result
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel(),
    NavigationEventDelegate<HomeScreenNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, _ ->
        emitNavigation(HomeScreenNavigation.InvalidResponse)
    }

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState(
            isDarkTheme = repo.getTheme()
        )
    )
    val state = _state.asStateFlow()

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    private fun updateTheme(isDarkTheme: Boolean) {
        repo.saveTheme(isDarkTheme)
        _state.update {
            it.copy(
                isDarkTheme = isDarkTheme
            )
        }
    }

    init {
        getUserForHome()
        getCommands("yuborildi")
        getCommands("qabulqildi")
        getCommands("bajarildi")
        getCommands("bajarilmadi")
        getCommands("kechikibbajarildi")
        getEmployees()
    }

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    private fun updateLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }

    private fun getEmployees() {
        try {
            updateLoading(true)
            viewModelScope.launch(handler) {
                repo.getEmployees().let { response ->
                    if (response.isSuccessful) {
                        updateLoading(false)
                        _state.update {
                            it.copy(
                                employees = response.body()?.results ?: listOf()
                            )
                        }
                    }else{
                        updateLoading(false)
                    }
                }
            }
        } catch (e: Exception) {
            updateLoading(false)
            emitNavigation(HomeScreenNavigation.FailedToLoadEmployees)
        }
    }

    private fun getUserForHome() {
        try {
            viewModelScope.launch(handler) {
                repo.getUserForHome().let { response ->
                    if (response.isSuccessful) {
                        _state.update {
                            it.copy(
                                username = response.body()!!.user.username
                            )
                        }
                        _state.update {
                            it.copy(
                                lastName = response.body()!!.user.lastName
                            )
                        }
                        _state.update {
                            it.copy(
                                email = response.body()!!.user.email

                            )
                        }
                        _state.update {
                            it.copy(
                                imageUrl = response.body()!!.user.image
                            )
                        }
                    } else {
                        val user = repo.getUserFromLocal()
                        _state.update {
                            it.copy(
                                username = user.username
                            )
                        }
                        _state.update {
                            it.copy(
                                email = user.email
                            )
                        }
                        _state.update {
                            it.copy(
                                lastName = user.lastName
                            )
                        }
                        _state.update {
                            it.copy(
                                imageUrl = user.image
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            val user = repo.getUserFromLocal()
            _state.update {
                it.copy(
                    username = user.username
                )
            }
            _state.update {
                it.copy(
                    lastName = user.lastName
                )
            }
            _state.update {
                it.copy(
                    email = user.email
                )
            }
            _state.update {
                it.copy(
                    imageUrl = user.image
                )
            }
        }
    }

    private fun logoutUser() {
        viewModelScope.launch(handler) {
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

    fun getCommands(status: String) {
        try {
            updateLoading(true)
            viewModelScope.launch(handler) {
                repo.getMessage(status).let { response ->
                    if (response.isSuccessful) {
                        updateLoading(false)
                        when (status) {
                            "yuborildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsSent = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "qabulqildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsReceived = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "bajarildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsDone = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "bajarilmadi" -> {
                                _state.update {
                                    it.copy(
                                        commandsNotDone = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "kechikibbajarildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsLateDone = response.body()?.results ?: listOf()
                                    )
                                }
                            }
                        }
                    } else {
                        updateLoading(false)
                    }
                }
            }
        } catch (e: Exception) {
            updateLoading(false)
        }
    }

    fun handleClickIntents(intent: HomeScreenClickIntents) {
        when (intent) {
            is HomeScreenClickIntents.ClearEmployeesCommandsList -> {
                _state.update {
                    it.copy(
                        employeesToSendCommand = listOf()
                    )
                }
            }
            is HomeScreenClickIntents.OnEmployeeClick -> {
                val employees = state.value.employeesToSendCommand.toMutableList()
                if(employees.contains("user[${intent.userId}]")){
                    employees.remove("user[${intent.userId}]")
                }else{
                    employees.add("user[${intent.userId}]")
                }
               _state.update {
                   it.copy(
                       employeesToSendCommand = employees
                   )
               }
            }

            is HomeScreenClickIntents.CreateCommands -> {
                emitNavigation(HomeScreenNavigation.OnCreateCommands(intent.users))
            }

            is HomeScreenClickIntents.OnThemeChange -> {
                updateTheme(intent.isDarkTheme)
            }

            HomeScreenClickIntents.LogoutClick -> {
                logoutUser()
            }

            is HomeScreenClickIntents.CallEmployee -> {
                emitNavigation(HomeScreenNavigation.CallEmployee(intent.phoneNumber))
            }

            is HomeScreenClickIntents.OnItemClick -> {
                emitNavigation(HomeScreenNavigation.OnItemClick(intent.workId))
            }

            is HomeScreenClickIntents.CreateCommand -> {
                emitNavigation(HomeScreenNavigation.NavigateToCompose(intent.userId))
            }

            is HomeScreenClickIntents.OnPullToRefreshCommands -> {
                getCommands(intent.status)
            }

            HomeScreenClickIntents.OnPullToRefreshEmployees -> {
                getEmployees()
//                _state.update { homeScreenState ->
//                    homeScreenState.copy(
//                        employees = Pager(
//                            PagingConfig(pageSize = 15)
//                        ) {
//                            EmployeesDataSource(repo) { isLoading ->
//                                _state.update {
//                                    it.copy(
//                                        isEmployeesLoading = isLoading
//                                    )
//                                }
//                            }
//                        }.flow.cachedIn(viewModelScope)
//                    )
//                }
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

    fun changeSelectedTabIndex(tabIndex: Int) {
        _state.update {
            it.copy(
                selectedTabIndex = tabIndex
            )
        }
    }

}

data class HomeScreenState(
    val isDarkTheme: Boolean,
    val tabItems: List<UiText> = listOf(
        UiText.StringResource(resId = R.string.commands_sent),
        UiText.StringResource(resId = R.string.commands_received),
        UiText.StringResource(resId = R.string.commands_done),
        UiText.StringResource(resId = R.string.commands_not_done),
        UiText.StringResource(resId = R.string.late_done),
        UiText.StringResource(resId = R.string.employees)
    ),
    val selectedTabIndex: Int = 0,
    val username: String = "",
    val lastName: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val isLoading: Boolean = false,
    val commandsSent: List<Result> = listOf(),
    val commandsReceived: List<Result> = listOf(),
    val commandsDone: List<Result> = listOf(),
    val commandsNotDone: List<Result> = listOf(),
    val commandsLateDone: List<Result> = listOf(),
    val employees: List<com.ierusalem.employeemanagement.features.home.presentation.employees.model.Result> = listOf(),
    val employeesToSendCommand : List<String> = listOf()
)
