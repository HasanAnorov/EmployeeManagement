package com.ierusalem.employeemanagement

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ierusalem.employeemanagement.databinding.ActivityMainBinding
import com.ierusalem.employeemanagement.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets -> insets }
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferenceHelper = PreferenceHelper(this)
        val destination = if(preferenceHelper.isLogged()) R.id.homeFragment else R.id.loginFragment
        Log.d("ahi3646_des", "onCreate:  ${preferenceHelper.isLogged()} $destination")
        val controller = findNavController()
        val inflater = controller.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        controller.graph = graph.apply {
            setStartDestination(destination)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp() || super.onSupportNavigateUp()
    }

    /**
     * See https://issuetracker.google.com/142847973
     */
    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        return navHostFragment.navController
    }

}

