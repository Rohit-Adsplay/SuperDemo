package com.superdemo.code.features.mainmodule

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.superdemo.code.R
import com.superdemo.code.core.utils.hapticFeedback
import com.superdemo.code.databinding.ActivityMainParentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainParent : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityMainParentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainParentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar.toolbar)

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dashboardFragment -> {
                  //  loadFragment(Dashboard())
                    hapticFeedback(applicationContext)
                    true
                }
                else -> {
                    false
                }
            }
        }

        binding.navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.appBar.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        if (savedInstanceState == null) {
           // loadFragment(Dashboard())
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_privacy -> {
                hapticFeedback(applicationContext)
                Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_delete -> {
                hapticFeedback(applicationContext)
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_support -> {
                hapticFeedback(applicationContext)
                Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_signout -> {
                hapticFeedback(applicationContext)
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            }

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainNavHost, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showDrawer() {
        binding.drawerLayout.openDrawer(Gravity.LEFT);
    }

    fun hideDrawer() {
        binding.drawerLayout.closeDrawer(Gravity.LEFT);
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}