package com.ierusalem.employeemanagement.features.personal_statistics.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.personal_statistics.presentation.PersonalStatisticsEvents
import com.ierusalem.employeemanagement.features.personal_statistics.presentation.PersonalStatisticsNavigation
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PersonalStatisticsViewModel : ViewModel(),
    NavigationEventDelegate<PersonalStatisticsNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(PersonalStatisticsNavigation.Failure)
    }

    private val _state: MutableStateFlow<PersonalStatisticsState> =
        MutableStateFlow(PersonalStatisticsState())
    val state = _state.asStateFlow()

    fun handleEvents(event: PersonalStatisticsEvents) {
        when(event){
            PersonalStatisticsEvents.OnNavIconClick -> {
                emitNavigation(PersonalStatisticsNavigation.OnNavIconClick)
            }
            is PersonalStatisticsEvents.TabItemClick -> {
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
data class PersonalStatisticsState(
    val tabItems: List<UiText> = listOf(
        UiText.StringResource(R.string.commands_sent),
        UiText.StringResource(R.string.commands_received)
    ),
    val selectedTabIndex: Int = 0
)