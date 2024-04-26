package com.ierusalem.employeemanagement.features.statistics.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.statistics.data.StatisticsRepository
import com.ierusalem.employeemanagement.features.statistics.presentation.StatisticsScreenEvents
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable

class StatisticsViewModel(private val repo: StatisticsRepository): ViewModel(),
    NavigationEventDelegate<StatisticsScreenNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(StatisticsScreenNavigation.Failure)
    }

    private val _state: MutableStateFlow<StatisticsUiState> = MutableStateFlow(StatisticsUiState())
    val state = _state.asStateFlow()

    init {
        getStatistics()
    }

    fun handleEvents(event: StatisticsScreenEvents){
        when(event){
            StatisticsScreenEvents.NavIconClick -> {
                emitNavigation(StatisticsScreenNavigation.NavIconClick)
            }
            StatisticsScreenEvents.DownloadStatistics -> {
                emitNavigation(StatisticsScreenNavigation.DownloadStatistics)
            }
        }
    }

    private fun getStatistics(){
        viewModelScope.launch(handler) {
            repo.getStatistics(pageSize = 10000000).let {
                if (it.isSuccessful){
                    Log.d("ahi3646", "getStatistics: ${it.body()} ")
                }else{
                    Log.d("ahi3646", "getStatistics: ${it.errorBody()} ")
                }
            }
        }
    }


}

@Immutable
data class StatisticsUiState(
    val downloadUrl: String = ""
)