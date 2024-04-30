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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable

class StatisticsViewModel(private val repo: StatisticsRepository) : ViewModel(),
    NavigationEventDelegate<StatisticsScreenNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(StatisticsScreenNavigation.Failure)
    }

    private val _state: MutableStateFlow<StatisticsUiState> = MutableStateFlow(StatisticsUiState())
    val state = _state.asStateFlow()

    init {
        getStatistics()
        getSoloStatistics()
    }

    fun setSuperUser(isSuperUser: Boolean){
        _state.update {
            it.copy(
                isSuperUser = isSuperUser
            )
        }
    }

    fun handleEvents(event: StatisticsScreenEvents) {
        when (event) {
            StatisticsScreenEvents.DownloadPersonalStatistics -> {
                emitNavigation(StatisticsScreenNavigation.DownloadPersonalStatistics)
            }
            StatisticsScreenEvents.NavIconClick -> {
                emitNavigation(StatisticsScreenNavigation.NavIconClick)
            }

            StatisticsScreenEvents.DownloadStatistics -> {
                emitNavigation(StatisticsScreenNavigation.DownloadStatistics)
            }
        }
    }

    private fun getSoloStatistics(){
        viewModelScope.launch(handler) {
            repo.getSoloStatistics(pageSize = 10000000).let {
                if (it.isSuccessful) {
                    val results = it.body()!!.results
                    var sent = 0
                    var done = 0
                    var notDone = 0
                    var lateDone = 0
                    var received = 0
                    results.forEach {result ->
                        sent += result.yuborildi
                        done += result.bajarildi
                        lateDone += result.kechikibbajarildi
                        notDone += result.bajarilmadi
                        received += result.qabulqildi
                    }
                    _state.update { state ->
                        state.copy(
                            soloSent = sent.toString(),
                            soloDone = done.toString(),
                            soloLateDone = lateDone.toString(),
                            soloNotDone = notDone.toString(),
                            soloReceived = received.toString()
                        )
                    }
                    Log.d("ahi3646", "getStatistics: ${it.body()} ")
                } else {
                    emitNavigation(StatisticsScreenNavigation.Failure)
                    Log.d("ahi3646", "getStatistics: ${it.errorBody()} ")
                }
            }
        }
    }

    private fun getStatistics() {
        viewModelScope.launch(handler) {
            repo.getStatistics(pageSize = 10000000).let {
                if (it.isSuccessful) {

                    val employees = mutableListOf("")
                    it.body()!!.results.forEach { result ->
                        employees.add("${result.username} ${result.lastName}")
                    }
                    val sent = mutableListOf("")
                    it.body()!!.results.forEach { result ->
                        sent.add(result.yuborildi.toString())
                    }
                    val received = mutableListOf("")
                    it.body()!!.results.forEach { result ->
                        received.add(result.qabulqildi.toString())
                    }
                    val done = mutableListOf("")
                    it.body()!!.results.forEach { result ->
                        done.add(result.bajarildi.toString())
                    }
                    val notDone = mutableListOf("")
                    it.body()!!.results.forEach { result ->
                        notDone.add(result.bajarilmadi.toString())
                    }
                    val lateDone = mutableListOf("")
                    it.body()!!.results.forEach { result ->
                        lateDone.add(result.kechikibbajarildi.toString())
                    }
                    _state.update { state ->
                        state.copy(
                            employees = employees,
                            sent = sent,
                            done = done,
                            lateDone = lateDone,
                            notDone = notDone,
                            received = received
                        )
                    }
                    Log.d("ahi3646", "getStatistics: ${it.body()} ")
                } else {
                    emitNavigation(StatisticsScreenNavigation.Failure)
                    Log.d("ahi3646", "getStatistics: ${it.errorBody()} ")
                }
            }
        }
    }
}

@Immutable
data class StatisticsUiState(
    val isSuperUser: Boolean = false,
    val downloadUrl: String = "",
    val employees: List<String> = listOf(),
    val sent: List<String> = listOf(),
    val received: List<String> = listOf(),
    val done: List<String> = listOf(),
    val notDone: List<String> = listOf(),
    val lateDone: List<String> = listOf(),
    val soloSent: String = "0",
    val soloReceived: String = "0",
    val soloDone: String = "0",
    val soloNotDone: String = "0",
    val soloLateDone: String = "0",
)