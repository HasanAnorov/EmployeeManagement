package com.ierusalem.employeemanagement.features.for_information.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepository
import com.ierusalem.employeemanagement.features.for_information.presentation.ForInformationEvents
import com.ierusalem.employeemanagement.features.for_information.presentation.ForInformationNavigation
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.annotation.concurrent.Immutable

class ForInformationViewModel(
    repository: ForInformationRepository
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
            }
        }
    }


}

@Immutable
data class ForInformationState(
    val isLoading: Boolean = false,
    val data: List<String> = listOf()
)