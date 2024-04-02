package com.ierusalem.employeemanagement.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.databinding.ActivityMainBinding
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val preferenceHelper = PreferenceHelper(this)
        val config = resources.configuration
        val lang = preferenceHelper.getLocal() // your language code
        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets -> insets }
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destination = if (preferenceHelper.isLogged()) {
            val userData = preferenceHelper.getUser()
            if (userData.isStaff) {
                R.id.homeFragment
            } else {
                R.id.staffHomeFragment
            }
        } else R.id.loginFragment
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

