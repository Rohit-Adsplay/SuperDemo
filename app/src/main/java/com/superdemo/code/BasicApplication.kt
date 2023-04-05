package com.superdemo.code

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.armcha.debugBanner.Banner
import io.armcha.debugBanner.DebugBanner
import timber.log.Timber

@HiltAndroidApp
class BasicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        if (BuildConfig.DEBUG) {
            DebugBanner.init(application = this)
            var Text = ""
            if (BuildConfig.FLAVOR.equals("dev")) {
                Text = "Test"
            } else if (BuildConfig.FLAVOR.equals("prod")) {
                Text = "UAT"
            }
            DebugBanner.init(
                application = this,
                banner = Banner(bannerText = Text)
            )
        }

    }
}
