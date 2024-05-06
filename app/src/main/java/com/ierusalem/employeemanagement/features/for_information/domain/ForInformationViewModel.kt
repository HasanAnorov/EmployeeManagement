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
import com.ierusalem.employeemanagement.utils.UiText
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
                //refresh informations
                getReceivedInformation()
                getSenInformation()
            }
            is ForInformationEvents.OnTabItemClick ->{
                _state.update {
                    it.copy(
                        selectedTabIndex = intent.tabIndex
                    )
                }
            }
        }
    }

    fun getReceivedInformation(){
        viewModelScope.launch(handler) {
            repository.getReceivedInformation().let {response ->
                if (response.isSuccessful){
                    _state.update {uiState ->
                        uiState.copy(
                            receivedInformation = response.body()!!.results.map {
                                ForInformationData(
                                    fullName = "${it.adminusername} ${it.adminlastName} ${it.patronymicName ?:""}",
                                    image = it.img,
                                    text = it.text,
                                    position = it.adminunvoni
                                )
                            }
                        )
                    }
                    Log.d("ahi3646", "getReceivedInformation: ${response.body()!!}")
                }else{
                    emitNavigation(ForInformationNavigation.InvalidResponse)
                    Log.d("ahi3646", "getReceivedInformation: false")
                }
            }
        }
    }

    fun getSenInformation(){
        viewModelScope.launch(handler) {
            repository.getSenInformation().let {response ->
                if (response.isSuccessful){
                    _state.update {state ->
                        state.copy(
                            sentInformation = response.body()!!.results.map {
                                ForInformationData(
                                    fullName = "${it.user} ${it.userLastname}",
                                    image = it.image,
                                    text = it.text,
                                    position = it.userUnvoni
                                )
                            }
                        )
                    }
                    Log.d("ahi3646", "getSenInformation: ${response.body()!!} ")
                }else{
                    emitNavigation(ForInformationNavigation.InvalidResponse)
                    Log.d("ahi3646", "getSenInformation: false")
                }
            }
        }
    }


}

@Immutable
data class ForInformationState(
    val isLoading: Boolean = false,

    val sentInformation: List<ForInformationData> = listOf(),
    val receivedInformation: List<ForInformationData> = listOf(),

    val selectedTabIndex: Int = 0,
    val tabItems: List<UiText> = listOf(
        UiText.StringResource(R.string.commands_sent),
        UiText.StringResource(R.string.commands_received),
    ),
)

data class ForInformationData(
    val fullName: String,
    val image: String,
    val text: String,
    val position: String?
)