package com.ierusalem.employeemanagement.features.compose.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme
import java.io.File
import java.io.FileOutputStream

class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                EmployeeManagementTheme {
                    ComposeScreen(
                        onAttachFileClick = {
                            showFileChooser()
                        },
                        onNavIconClicked = {
                            findNavController().popBackStack()
                        }
                    )
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100){
            if (resultCode == Activity.RESULT_OK) {
                Log.d("ahi3646", "onActivityResult: RESULT_OK - ${data?.data}")
                val inputStream =
                    requireContext().contentResolver.openInputStream(data?.data!!)

                val file = File.createTempFile(
                    "file",
                    "",
                    requireActivity().externalCacheDir
                )
                val fileOutputStream = FileOutputStream(file)
                inputStream?.copyTo(fileOutputStream)
                fileOutputStream.close()
                Log.d("ahi3646", "onActivityResult: ${file.name} ${file.absoluteFile}")
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
                Log.d("ahi3646", "onActivityResult: RESULT CANCELED ")
            }
        }
    }

    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 100)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Please install a file manager", Toast.LENGTH_SHORT)
                .show()
        }
    }

}