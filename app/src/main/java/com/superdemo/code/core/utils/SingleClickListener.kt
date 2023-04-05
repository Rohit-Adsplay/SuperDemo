package com.superdemo.code.core.utils

import android.os.SystemClock
import android.view.View

class SingleClickListener(private val block: () -> Unit) : View.OnClickListener {
    private var lastClickTime = 0L

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1500) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        block()
    }
}

fun View.setOnSingleClickListener(block: () -> Unit) {
    setOnClickListener(SingleClickListener(block))
}