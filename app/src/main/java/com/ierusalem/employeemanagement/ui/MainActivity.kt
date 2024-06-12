package com.ierusalem.employeemanagement.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.core.endless_service.Actions
import com.ierusalem.employeemanagement.core.endless_service.EndlessService
import com.ierusalem.employeemanagement.core.endless_service.ServiceState
import com.ierusalem.employeemanagement.core.endless_service.getServiceState
import com.ierusalem.employeemanagement.databinding.ActivityMainBinding
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import java.util.Locale

@Suppress("DEPRECATION")
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

        actionOnService(Actions.START)

    }

    @Suppress("SameParameterValue")
    private fun actionOnService(action: Actions) {
        if (getServiceState(this) == ServiceState.STOPPED && action == Actions.STOP) return
        Intent(this, EndlessService::class.java).also {
            it.action = action.name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                log("Starting the service in >=26 Mode")
                startForegroundService(it)
                return
            }
            log("Starting the service in < 26 Mode")
            startService(it)
        }
    }

    companion object{
        fun log(msg: String) = Log.d("ahi3646", msg)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
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

