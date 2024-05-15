package com.ierusalem.employeemanagement.features.staff_for_information.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepository
import com.ierusalem.employeemanagement.features.for_information.domain.ForInformationData
import com.ierusalem.employeemanagement.features.staff_for_information.presentation.StaffForInformationEvents
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StaffForInformationViewmodel(
    private val repository: ForInformationRepository
):ViewModel(),
    NavigationEventDelegate<StaffForInformationNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(StaffForInformationNavigation.InvalidResponse)
    }

    private val _state: MutableStateFlow<StaffForInformationState> =
        MutableStateFlow(StaffForInformationState())
    val state = _state.asStateFlow()

    private fun updateLoading(isLoading: Boolean){
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }

    fun getReceivedInformationBadgeCount() {
        viewModelScope.launch(handler) {
            repository.getReceivedInformationBadgeCount("kurilmagan").let { response ->
                if (response.isSuccessful) {
                    _state.update {
                        it.copy(
                            badgeCount = response.body()!!.count
                        )
                    }
                }
            }
        }
    }

    fun getReceivedInformation(){
        updateLoading(true)
        viewModelScope.launch(handler) {
            repository.getReceivedInformation(id = "").let {response ->
                if (response.isSuccessful){
                    updateLoading(false)
                    _state.update {uiState ->
                        uiState.copy(
                            receivedInformation = response.body()!!.results.map {
                                ForInformationData(
                                    fullName = "${it.adminusername} ${it.adminlastName} ${it.patronymicName ?:""}",
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
                }else{
                    updateLoading(false)
                    emitNavigation(StaffForInformationNavigation.InvalidResponse)
                    Log.d("ahi3646", "getReceivedInformation: false")
                }
            }
        }
    }

    fun handleClickIntents(intent: StaffForInformationEvents) {
        when (intent) {
            StaffForInformationEvents.NavIconClick -> {
                emitNavigation(StaffForInformationNavigation.ArrowBackClick)
            }
            StaffForInformationEvents.OnPullRefresh ->{
                getReceivedInformation()
                getReceivedInformationBadgeCount()
            }
        }
    }

}

@Immutable
data class StaffForInformationState(
    val isLoading: Boolean = false,
    val receivedInformation: List<ForInformationData> = listOf(),
    val badgeCount: Int = 0,
)

sealed interface StaffForInformationNavigation{
    data object ArrowBackClick: StaffForInformationNavigation
    data object InvalidResponse: StaffForInformationNavigation
}
