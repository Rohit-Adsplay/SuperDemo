package com.superdemo.code.features.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.superdemo.code.R
import com.superdemo.code.core.utils.startDestination
import com.superdemo.code.features.authmodule.AuthParent

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startDestination(AuthParent())
    }
}