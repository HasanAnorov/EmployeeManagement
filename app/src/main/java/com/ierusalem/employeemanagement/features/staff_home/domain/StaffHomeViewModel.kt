package com.ierusalem.employeemanagement.features.staff_home.domain

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.staff_home.data.model.response_messages.Result
import com.ierusalem.employeemanagement.features.staff_home.presentation.StaffHomeScreenEvents
import com.ierusalem.employeemanagement.features.staff_home.presentation.StaffHomeScreenNavigation
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.utils.Resource
import com.ierusalem.employeemanagement.utils.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StaffHomeViewModel(private val repo: StaffHomeRepository) : ViewModel(),
    NavigationEventDelegate<StaffHomeScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<StaffHomeScreenState> =
        MutableStateFlow(StaffHomeScreenState())
    val state = _state.asStateFlow()

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    private fun getUserForHome(){
        try {
            viewModelScope.launch {
                repo.getUserForHome().let {response ->
                    if(response.isSuccessful){
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
                    }else{
                        val user =repo.getUserFromLocal()
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
        }catch (e: Exception){
            val user =repo.getUserFromLocal()
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

    init {
        getUserForHome()
        getUserMessages("yuborildi")
        getUserMessages("qabulqildi")
        getUserMessages("bajarildi")
        getUserMessages("bajarilmadi")
    }

    fun getUserMessages(status: String) {
        try {
            updateLoading(true)
            viewModelScope.launch {
                repo.getUserMessages(status).let { response ->
                    if (response.isSuccessful) {
                        updateLoading(false)
                        when(status){
                            "yuborildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsSent = Resource.Success(response.body()?.results ?: listOf())
                                    )
                                }
                            }
                            "qabulqildi" ->{
                                _state.update {
                                    it.copy(
                                        commandsReceived = Resource.Success(response.body()?.results ?: listOf())
                                    )
                                }
                            }
                            "bajarildi" ->{
                                _state.update {
                                    it.copy(
                                        commandsDone = Resource.Success(response.body()?.results ?: listOf())
                                    )
                                }
                            }
                            "bajarilmadi" ->{
                                _state.update {
                                    it.copy(
                                        commandsNotDone = Resource.Success(response.body()?.results ?: listOf())
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
            when(status){
                "yuborildi" -> {
                    _state.update {
                        it.copy(
                            commandsSent = Resource.Failure("Unsuccessful response - ${e.localizedMessage}")
                        )
                    }
                }
                "qabulqildi" ->{
                    _state.update {
                        it.copy(
                            commandsReceived = Resource.Failure("Unsuccessful response - ${e.localizedMessage}")
                        )
                    }
                }
                "bajarildi" ->{
                    _state.update {
                        it.copy(
                            commandsDone = Resource.Failure("Unsuccessful response - ${e.localizedMessage}")
                        )
                    }
                }
                "bajarilmadi" ->{
                    _state.update {
                        it.copy(
                            commandsNotDone = Resource.Failure("Unsuccessful response - ${e.localizedMessage}")
                        )
                    }
                }
            }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }

    private fun logoutUser() {
        viewModelScope.launch {
            repo.logoutUser().let { response ->
                if (response.isSuccessful) {
                    repo.deleteToken()
                    repo.deleteRefreshToken()
                    emitNavigation(StaffHomeScreenNavigation.NavigateToLogin)
                } else {
                    emitNavigation(StaffHomeScreenNavigation.FailedToLogout)
                }
            }
        }
    }

    fun handleEvents(event: StaffHomeScreenEvents) {
        when (event) {
            StaffHomeScreenEvents.LogoutClick ->{
                logoutUser()
            }
            is StaffHomeScreenEvents.OnItemClick ->{
                emitNavigation(StaffHomeScreenNavigation.OnItemClick(event.workId))
            }
            is StaffHomeScreenEvents.OnPullToRefreshCommands -> getUserMessages(event.status)
            is StaffHomeScreenEvents.TabItemClick -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = event.tabIndex
                    )
                }
            }
        }
    }

}

@Immutable
data class StaffHomeScreenState(
    val tabItems: List<UiText> = listOf(
        UiText.StringResource(resId = R.string.commands_sent),
        UiText.StringResource(resId = R.string.commands_received),
        UiText.StringResource(resId = R.string.commands_done),
        UiText.StringResource(resId = R.string.commands_not_done),
    ),
    val isLoading: Boolean = false,
    val username: String = "",
    val lastName: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val commandsReceived:Resource<List<Result>> = Resource.Loading(),
    val commandsDone: Resource<List<Result>> = Resource.Loading(),
    val commandsNotDone: Resource<List<Result>> = Resource.Loading(),
    val commandsSent: Resource<List<Result>> = Resource.Loading(),
    val selectedTabIndex: Int = 0,
)