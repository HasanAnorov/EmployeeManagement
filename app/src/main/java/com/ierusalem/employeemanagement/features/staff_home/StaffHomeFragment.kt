package com.ierusalem.employeemanagement.features.staff_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.ierusalem.employeemanagement.ui.theme.EmployeeManagementTheme

class StaffHomeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
           setContent {
               EmployeeManagementTheme {
                   StaffHomeScreen()
               }
           }
        }
    }

}