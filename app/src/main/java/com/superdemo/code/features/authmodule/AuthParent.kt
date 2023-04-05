package com.superdemo.code.features.authmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.superdemo.code.R
import com.superdemo.code.databinding.ActivityAuthParentBinding

class AuthParent : AppCompatActivity() {

    private var _binding: ActivityAuthParentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthParentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var navHostFragment =
            supportFragmentManager.findFragmentById(R.id.authNavHost) as NavHostFragment
        var navController = navHostFragment.navController
        navController = findNavController(R.id.authNavHost)

    }
}