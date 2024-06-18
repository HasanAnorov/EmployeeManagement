package com.ierusalem.employeemanagement.features.for_information_edit.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

class ForInformationEditViewModel: ViewModel(),
    NavigationEventDelegate<ForInformationEditNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<ForInformationEditState> = MutableStateFlow(ForInformationEditState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(ForInformationEditNavigation.Failure)
    }

    fun onFilesChanged(file: File) {
        val newFiles = state.value.files.toMutableList().apply {
            add(file)
        }
        _state.update {
            it.copy(
                files = newFiles
            )
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


}

data class ForInformationEditState(
    val showAlertDialog: Boolean = false,
    val textForm: String = "",
    val files: List<File> = arrayListOf(),
    var isSubmitting: Boolean = false
)