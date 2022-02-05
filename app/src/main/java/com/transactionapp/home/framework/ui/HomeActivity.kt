package com.transactionapp.home.framework.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.transactionapp.R
import com.transactionapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_transaction_app) as NavHostFragment
        navController = navHostFragment.navController

        binding?.bottomNavigation?.setOnNavigationItemSelectedListener { menuItem ->
            setCurrentFragment(menuItem)
        }
    }

    private fun setCurrentFragment(menuItem: MenuItem): Boolean{
        return when (menuItem.title) {
            AUTHORIZE_ACTION -> {
                navController.navigate(R.id.transactionAuthorizationFragment)
                true
            }
            GO_HOME_ACTION -> {
                navController.navigate(R.id.homeFragment)
                true
            }
            SEARCH_ACTION -> {
                navController.navigate(R.id.searchTransactionFragment)
                true
            }
            SHOW_ACTION -> {
                navController.navigate(R.id.listTransactionFragment)
                true
            }
            CANCEL_ACTION -> {
                navController.navigate(R.id.transactionAnnulmentFragment)
                true
            }
            else -> false
        }
    }

    companion object{
        const val AUTHORIZE_ACTION = "Autorizar"
        const val SEARCH_ACTION = "Buscar"
        const val GO_HOME_ACTION = "Home"
        const val SHOW_ACTION = "Mostrar"
        const val CANCEL_ACTION = "Anular"
    }
}