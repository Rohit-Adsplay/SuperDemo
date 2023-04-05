package com.superdemo.code.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings

fun Context.startDestination(activity: Activity, bundle: Bundle? = null) {
    if (bundle != null) {
        val intent = Intent(this, activity::class.java)
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    } else {
        startActivity(Intent(this, activity::class.java))
    }
}

fun Context.openSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        val uri: Uri = Uri.fromParts("package", packageName, null)
        data = uri
    }
    startActivity(intent)
}
