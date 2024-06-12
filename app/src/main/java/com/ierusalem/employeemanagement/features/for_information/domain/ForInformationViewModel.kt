package com.ierusalem.employeemanagement.features.for_information.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepository
import com.ierusalem.employeemanagement.features.for_information.presentation.ForInformationEvents
import com.ierusalem.employeemanagement.features.for_information.presentation.ForInformationNavigation
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.core.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable

class ForInformationViewModel(
    private val repository: ForInformationRepository
) : ViewModel(),
    NavigationEventDelegate<ForInformationNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(ForInformationNavigation.InvalidResponse)
    }

    private val _state: MutableStateFlow<ForInformationState> =
        MutableStateFlow(ForInformationState())
    val state = _state.asStateFlow()

    fun handleClickIntents(intent: ForInformationEvents) {
        when (intent) {
            ForInformationEvents.NavIconClick -> {
                emitNavigation(ForInformationNavigation.ArrowBackClick)
            }

            ForInformationEvents.OnPullRefresh -> {
                //refresh information
                getReceivedInformationBadgeCount()
                getReceivedInformation()
                getSenInformation()
            }

            is ForInformationEvents.OnTabItemClick -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = intent.tabIndex
                    )
                }
            }
        }
    }

    fun getReceivedInformation() {
        viewModelScope.launch(handler) {
            repository.getReceivedInformation(id = "").let { response ->
                if (response.isSuccessful) {
                    _state.update { uiState ->
                        uiState.copy(
                            receivedInformation = response.body()!!.results.map {
                                ForInformationData(
                                    fullName = "${it.adminusername} ${it.adminlastName} ${it.patronymicName ?: ""}",
                                    image = it.img,
                                    text = it.text,
                                    position = it.adminunvoni,
                                    id = it.id,
                                    isSeen = it.status != "kurilmagan"
                                )
                            }
                        )
                    }
                    Log.d("ahi3646", "getReceivedInformation: ${response.body()!!}")
                } else {
                    emitNavigation(ForInformationNavigation.InvalidResponse)
                    Log.d("ahi3646", "getReceivedInformation: false")
                }
            }
        }
    }

    fun getReceivedInformationBadgeCount() {
        viewModelScope.launch(handler) {
            repository.getReceivedInformationBadgeCount("kurilmagan").let { response ->
                if (response.isSuccessful) {
                    _state.update {
                        it.copy(
                            receivedBadgeCount = response.body()!!.count
                        )
                    }
                }
            }
        }
    }

    fun getSenInformation() {
        updateLoading(true)
        viewModelScope.launch(handler) {
            repository.getSenInformation(id = "").let { response ->
                if (response.isSuccessful) {
                    updateLoading(false)
                    _state.update { state ->
                        state.copy(
                            sentInformation = response.body()!!.results.map {
                                ForInformationData(
                                    fullName = "${it.user} ${it.userLastname}",
                                    image = it.image,
                                    text = it.text,
                                    position = it.userUnvoni,
                                    id = it.id,
                                    isSeen = true
                                )
                            }
                        )
                    }
                    Log.d("ahi3646", "getSenInformation: ${response.body()!!} ")
                } else {
                    updateLoading(false)
                    emitNavigation(ForInformationNavigation.InvalidResponse)
                    Log.d("ahi3646", "getSenInformation: false")
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


}

@Immutable
data class ForInformationState(
    val isLoading: Boolean = false,

    val receivedBadgeCount: Int = 0,

    val sentInformation: List<ForInformationData> = listOf(),
    val receivedInformation: List<ForInformationData> = listOf(),

    val selectedTabIndex: Int = 0,
    val tabItems: List<UiText> = listOf(
        UiText.StringResource(R.string.commands_sent),
        UiText.StringResource(R.string.commands_received),
    ),
)

data class ForInformationData(
    val id: Int,
    val fullName: String,
    val image: String,
    val text: String,
    val position: String?,
    val isSeen: Boolean
)