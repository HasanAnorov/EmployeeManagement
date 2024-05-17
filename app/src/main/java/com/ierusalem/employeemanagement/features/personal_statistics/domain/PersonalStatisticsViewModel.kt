package com.ierusalem.employeemanagement.features.personal_statistics.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.personal_statistics.data.PersonalStatisticsRepository
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
import kotlinx.coroutines.launch

class PersonalStatisticsViewModel(
    private val repository: PersonalStatisticsRepository
) : ViewModel(),
    NavigationEventDelegate<PersonalStatisticsNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(PersonalStatisticsNavigation.Failure)
    }

    private val _state: MutableStateFlow<PersonalStatisticsState> =
        MutableStateFlow(PersonalStatisticsState())
    val state = _state.asStateFlow()

    fun getPersonalStatisticsSent() {
        viewModelScope.launch(handler) {
            repository.getPersonalStatisticsSent(pageSize = 10000000).let {
                if (it.isSuccessful){
                    val results = it.body()!!.results
                    var soloSent = 0
                    var soloDone = 0
                    var soloNotDone = 0
                    var soloLateDone = 0
                    var soloReceived = 0
                    results.forEach {result ->
                        soloSent += result.yuborildi
                        soloDone += result.bajarildi
                        soloNotDone += result.bajarilmadi
                        soloLateDone += result.kechikibbajarildi
                        soloReceived += result.qabulqildi
                    }
                    _state.update { state ->
                        state.copy(
                            sentSoloSent = soloSent.toString(),
                            sentSoloDone = soloDone.toString(),
                            sentSoloLateDone = soloLateDone.toString(),
                            sentSoloNotDone = soloNotDone.toString(),
                            sentSoloReceived = soloReceived.toString()
                        )
                    }
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
                            sentEmployees = employees,
                            sentSent = sent,
                            sentDone = done,
                            sentLateDone = lateDone,
                            sentNotDone = notDone,
                            sentReceived = received
                        )
                    }
                }else{
                    emitNavigation(PersonalStatisticsNavigation.Failure)
                }
            }
        }
    }

    fun getPersonalStatisticsReceived() {
        viewModelScope.launch(handler) {
            repository.getPersonalStatisticsReceived(pageSize = 10000000).let {
                if (it.isSuccessful){
                    val results = it.body()!!.results
                    var soloSent = 0
                    var soloDone = 0
                    var soloNotDone = 0
                    var soloLateDone = 0
                    var soloReceived = 0
                    results.forEach {result ->
                        soloSent += result.yuborildi
                        soloDone += result.bajarildi
                        soloNotDone += result.bajarilmadi
                        soloLateDone += result.kechikibbajarildi
                        soloReceived += result.qabulqildi
                    }
                    _state.update { state ->
                        state.copy(
                            soloSent = soloSent.toString(),
                            soloDone = soloDone.toString(),
                            soloLateDone = soloLateDone.toString(),
                            soloNotDone = soloNotDone.toString(),
                            soloReceived = soloReceived.toString()
                        )
                    }
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
                }else{
                    emitNavigation(PersonalStatisticsNavigation.Failure)
                }
            }
        }
    }

    fun handleEvents(event: PersonalStatisticsEvents) {
        when (event) {
            PersonalStatisticsEvents.OnNavIconClick -> {
                emitNavigation(PersonalStatisticsNavigation.OnNavIconClick)
            }

            PersonalStatisticsEvents.DownloadPersonalStatisticsSent -> {
                emitNavigation(PersonalStatisticsNavigation.DownloadPersonalStatisticsSent)
            }

            PersonalStatisticsEvents.DownloadPersonalStatisticsReceived -> {
                emitNavigation(PersonalStatisticsNavigation.DownloadPersonalStatisticsReceived)
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
    val selectedTabIndex: Int = 0,

    val sentEmployees: List<String> = listOf(),
    val sentSent: List<String> = listOf(),
    val sentReceived: List<String> = listOf(),
    val sentDone: List<String> = listOf(),
    val sentNotDone: List<String> = listOf(),
    val sentLateDone: List<String> = listOf(),

    val sentSoloSent: String = "0",
    val sentSoloReceived: String = "0",
    val sentSoloDone: String = "0",
    val sentSoloNotDone: String = "0",
    val sentSoloLateDone: String = "0",

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