package com.ierusalem.employeemanagement.features.create_work.presentation

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.OpenableColumns
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.presentation.Users
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.core.utils.Constants
import com.ierusalem.employeemanagement.core.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class ComposeFragment : Fragment() {

    private val viewModel: ComposeViewmodel by viewModel()

    private val getFilesLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val contentResolver = activity?.contentResolver
            val data: Intent = result.data!!

            var fileName = "file"
            var fileSize: Long? = null

            data.data?.let { returnUri ->
                contentResolver?.query(returnUri, null, null, null, null)
            }?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                fileName = cursor.getString(nameIndex)
                fileSize = cursor.getLong(sizeIndex)
            }

            if (fileSize != null) {
                val inputStream = requireContext().contentResolver.openInputStream(data.data!!)
                val filePathToSave =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

                val file = File(filePathToSave, fileName)
                val fileOutputStream = FileOutputStream(file)
                inputStream?.copyTo(fileOutputStream)
                fileOutputStream.close()
                viewModel.onFilesChanged(file)
            }
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            Log.d("ahi3646", "onActivityResult: RESULT CANCELED ")
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val usersString = arguments?.getString(Constants.USERS_TO_COMMAND) ?: ""
        val userId = arguments?.getString(Constants.COMPOSE_PROFILE_ID) ?: ""
        val users = Gson().fromJson(usersString, Users::class.java)

        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                EmployeeManagementTheme {
                    ComposeScreen(
                        onAttachFileClick = {
                            if (Environment.isExternalStorageManager()) {
                                showFileChooser()
                            } else {
                                viewModel.showAlertDialog(true)
                            }
                        },
                        onNavIconClicked = { navigateToHomeWithRefresh() },
                        onTextChanged = { viewModel.onTextFormChanged(it) },
                        onSubmitClicked = {
                            if (usersString.isNotEmpty()) {
                                viewModel.onSubmitClickedForUsers(users.users)
                            } else {
                                viewModel.onSubmitClicked(userId)
                            }
                        },
                        onYearChanged = { viewModel.onYearChanged(it) },
                        onMonthChanged = { viewModel.onMonthChanged(it) },
                        onDayChanged = { viewModel.onDayChanged(it) },
                        dismissDialog = { viewModel.showAlertDialog(false) },
                        gotoStorageSetting = {
                            if (Build.VERSION.SDK_INT >= 30) {
                                val getPermission = Intent()
                                getPermission.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                                startActivity(getPermission)
                            }
                            viewModel.showAlertDialog(false)
                        },
                        onTaskClick = {
                            viewModel.changeIsCommand(true)
                        },
                        onInformationClick = {
                            viewModel.changeIsCommand(false)
                        },
                        state = state
                    )
                }
            }
        }
    }

    private fun navigateToHomeWithRefresh() {
        val bundle = Bundle()
        bundle.putBoolean(Constants.COMPOSE_COMMAND, true)
        findNavController().navigate(R.id.action_composeFragment_to_homeFragment, bundle)
    }

    private fun showFileChooser() {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.flags = FLAG_GRANT_READ_URI_PERMISSION or FLAG_GRANT_WRITE_URI_PERMISSION
        try {
            getFilesLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                getString(R.string.please_install_a_file_manager), Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenNavigation.executeWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            action = ::executeNavigation
        )
    }

    private fun executeNavigation(navigation: ComposeScreenNavigation) {
        when (navigation) {
            ComposeScreenNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_not_create_a_command), Toast.LENGTH_SHORT
                ).show()
            }

            ComposeScreenNavigation.Success -> {
                navigateToHomeWithRefresh()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.the_command_has_been_created), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}