package com.ierusalem.employeemanagement.features.work_description.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.downloader.AndroidDownloader
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionViewModel
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.executeWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class WorkDescriptionFragment : Fragment() {

    private val viewModel: WorkDescriptionViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val workId = arguments?.getString(Constants.WORK_DESCRIPTION_KEY)
        val isFromHome = arguments?.getBoolean(Constants.WORK_DESCRIPTION_KEY_FROM_HOME) ?: false
        if (isFromHome) {
            viewModel.isFromHome(true)
        }
        if (workId != null && !isFromHome) {
            viewModel.getMessageById(workId)
        }
        if (isFromHome && workId != null) {
            viewModel.getMessageByIdAdmin(workId)
        }
        if (workId == null) {
            Toast.makeText(
                requireContext(),
                getString(R.string.something_went_wrong),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                EmployeeManagementTheme {
                    WorkDescriptionScreen(
                        state = state,
                        intentReducer = {
                            viewModel.handleEvents(it)
                        },
                        dismissDialog = { viewModel.showAlertDialog(false) },
                        gotoStorageSetting = {
                            if (Build.VERSION.SDK_INT >= 30) {
                                val getPermission = Intent()
                                getPermission.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                                startActivity(getPermission)
                            }
                            viewModel.showAlertDialog(false)
                        },
                    )
                }
            }
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.flags =
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
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

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenNavigation.executeWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            action = ::executeNavigation
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun executeNavigation(navigation: WorkDescriptionNavigation) {
        when (navigation) {
            WorkDescriptionNavigation.AttachFileClick -> {
                if (Environment.isExternalStorageManager()) {
                    showFileChooser()
                } else {
                    viewModel.showAlertDialog(true)
                }
            }

            WorkDescriptionNavigation.InvalidResponse -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_t_get_work_description),
                    Toast.LENGTH_SHORT
                ).show()
            }

            WorkDescriptionNavigation.InvalidResponseMarkAsDone -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_t_mark_this_work_as_done),
                    Toast.LENGTH_SHORT
                ).show()
            }

            is WorkDescriptionNavigation.DownloadFile -> {
                val downloader = AndroidDownloader(requireContext())
                downloader.downloadFile(navigation.url)
            }

            WorkDescriptionNavigation.MarkedAsDone -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.work_marked_as_done), Toast.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }

            WorkDescriptionNavigation.NavIconClick -> {
                findNavController().popBackStack()
            }
        }
    }

    private val getFilesLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val contentResolver = activity?.contentResolver
            val data: Intent = it.data!!

            var fileName = "file"
            var fileSize: Long? = null

            data.data?.let { returnUri ->
                contentResolver?.query(returnUri, null, null, null, null)
            }?.use { cursor ->
                /*
                 * Get the column indexes of the data in the Cursor,
                 * move to the first row in the Cursor, get the data,
                 * and display it.
                 */
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
        if (it.resultCode == Activity.RESULT_CANCELED) {
            // Write your code if there's no result
            Log.d("ahi3646", "onActivityResult: RESULT CANCELED ")
        }
    }

}