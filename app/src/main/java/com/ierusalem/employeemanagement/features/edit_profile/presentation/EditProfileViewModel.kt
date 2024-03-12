package com.ierusalem.employeemanagement.features.edit_profile.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditProfileViewModel : ViewModel() {

    private val _state: MutableStateFlow<EditProfileScreenState> = MutableStateFlow(
        EditProfileScreenState()
    )
    val state = _state.asStateFlow()

    fun updateImageUri(uri: Uri?) {
        _state.update {
            it.copy(imageUri = uri)
        }
    }

    fun onUsernameChanged(username: String){
        _state.update {
            it.copy(newUsername = username)
        }
    }

    fun onLastnameChanged(lastname: String){
        _state.update {
            it.copy(newLastname = lastname)
        }
    }

    fun onPositionChanged(position: String){
        _state.update {
            it.copy(newPosition = position)
        }
    }

    fun onEmailChanged(email: String){
        _state.update {
            it.copy(newEmail = email)
        }
    }

    fun onPhoneNumberChanged(phoneNumber: String){
        _state.update {
            it.copy(newPhoneNumber = phoneNumber)
        }
    }

    fun onRoomChanged(room: String){
        _state.update {
            it.copy(newRoom = room)
        }
    }

}


data class EditProfileScreenState(
    val imageUri: Uri? = null,
    val newPassword: String = "",
    val newUsername: String = "",
    val newLastname: String = "",
    val newEmail: String = "",
    val newPosition: String = "",
    val newRoom: String = "",
    val newPhoneNumber: String = ""
)