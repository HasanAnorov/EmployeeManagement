package com.ierusalem.employeemanagement.features.information_description.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepository
import com.ierusalem.employeemanagement.features.for_information.data.model.InformationResponse
import com.ierusalem.employeemanagement.features.for_information.data.model_received.ForInformationReceivedResponse
import com.ierusalem.employeemanagement.features.information_description.presentation.InformationDescriptionNavigation
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.core.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InformationDescriptionViewmodel(
    private val informationDescriptionRepository: ForInformationRepository
) : ViewModel(), NavigationEventDelegate<InformationDescriptionNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<InformationDescState> =
        MutableStateFlow(InformationDescState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(InformationDescriptionNavigation.Failure)
    }

    fun getDescription(isSent:Boolean,id:String){
        viewModelScope.launch(handler) {
            if(isSent){
                informationDescriptionRepository.getSenInformation(id).let {
                    if (it.isSuccessful) {
                        _state.update { state ->
                            state.copy(
                                description = Resource.Success(it.body()!!)
                            )
                        }
                    }else{
                        _state.update { state ->
                            state.copy(
                                description = Resource.Failure(it.message())
                            )
                        }
                    }
                }
            }else{
                informationDescriptionRepository.getReceivedInformationDesc(id).let {
                    if (it.isSuccessful){
                        _state.update {state ->
                            state.copy(
                                descriptionReceived = Resource.Success(it.body()!!)
                            )
                        }
                    }else{
                        _state.update {state->
                            state.copy(
                                descriptionReceived = Resource.Failure(it.message())
                            )
                        }
                    }
                }
            }
        }
    }

}

@Immutable
data class InformationDescState(
    val description: Resource<InformationResponse> = Resource.Loading(),
    val descriptionReceived: Resource<ForInformationReceivedResponse> = Resource.Loading()
)