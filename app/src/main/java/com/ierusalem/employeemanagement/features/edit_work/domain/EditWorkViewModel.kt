package com.ierusalem.employeemanagement.features.edit_work.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.compose.presentation.ComposeScreenNavigation
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.Calendar
import java.util.TimeZone

class EditWorkViewModel(
    private val editWorkRepository: EditWorkRepository
) : ViewModel(),
    NavigationEventDelegate<EditWorkScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<EditWorkUiState> = MutableStateFlow(EditWorkUiState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(EditWorkScreenNavigation.InvalidResponse)
    }

    fun deleteWorkById(workId: String) {
        viewModelScope.launch(handler) {
            editWorkRepository.deleteWorkById(workId).let {
                if (it.isSuccessful) {
                    emitNavigation(EditWorkScreenNavigation.SuccessOnWorkDeletion)
                } else {
                    emitNavigation(EditWorkScreenNavigation.FailureOnWorkDeletion)
                }
            }
        }
    }

    fun showAlertDialog(isShown: Boolean) {
        _state.update {
            it.copy(
                showAlertDialog = isShown
            )
        }
    }

    fun onTextFormChanged(textForm: String) {
        _state.update {
            it.copy(
                textForm = textForm
            )
        }
    }

    fun onYearChanged(year: String) {
        _state.update {
            it.copy(
                yearForm = year
            )
        }
    }

    fun onMonthChanged(month: String) {
        _state.update {
            it.copy(
                monthForm = month
            )
        }
    }

    fun onDayChanged(day: String) {
        _state.update {
            it.copy(
                dayForm = day
            )
        }
    }

}

sealed interface EditWorkScreenNavigation {
    data object InvalidResponse : EditWorkScreenNavigation
    data object SuccessOnWorkDeletion : EditWorkScreenNavigation
    data object FailureOnWorkDeletion : EditWorkScreenNavigation
}


@Immutable
data class EditWorkUiState(
    val showAlertDialog: Boolean = false,
    val textForm: String = "",
    val yearForm: String = Calendar
        .getInstance(TimeZone.getDefault())
        .get(Calendar.YEAR)
        .toString(),
    val monthForm: String = "",
    val dayForm: String = "",
    val files: List<File> = arrayListOf(),
)