package com.superdemo.code.core.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Vibrator
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun Context.closeKeyboard(root: View) {
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(root.windowToken, 0)
}

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackBar.setAction("Retry") {
            it()
        }
    }
    snackBar.show()
}

fun handleApiError(
    error: String,
    view: View,
) {
    when (error) {
        Constants.NO_INTERNET -> view.snackBar(Constants.SESSION_EXPIRED_MSG)
        Constants.SESSION_EXPIRED -> view.snackBar(Constants.SESSION_EXPIRED_MSG)
        Constants.NOT_FOUND -> view.snackBar(Constants.NOT_FOUND_MSG)
        else -> {
            Timber.e("Failed ${error}")
            view.snackBar(error)
        }
    }
}

fun String.copyToClipboard(context: Context) {
    val clipboard: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", this)
    clipboard.setPrimaryClip(clip)
}

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun isValidPassword(password: String?): Boolean {
    password?.let {
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–_[{}]:;',?/*~\$^+=<>]).{8,36}\$"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.find(password) != null
    } ?: return false
}

fun isEmailValid(email: String): Boolean {
    return Pattern.compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    ).matcher(email).matches()
}

fun isPanValid(pan: String): Boolean {
    return Pattern.compile(
        "[A-Z]{5}[0-9]{4}[A-Z]{1}"
    ).matcher(pan).matches()
}

fun isPhoneValid(phone: String): Boolean {
    return Pattern.compile(
        "^([+]\\d{2}[ ])?\\d{10,10}\$"
    ).matcher(phone).matches()
}

fun hapticFeedback(context: Context) {
    val myVib: Vibrator? =
        context.getSystemService(android.content.Context.VIBRATOR_SERVICE) as Vibrator?
    myVib!!.vibrate(50);
}

fun String.toReqBody(): RequestBody {
    return this.toRequestBody(("multipart/form-data".toMediaTypeOrNull()))
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun changeDateFormat(date: String?, foundFormat: String?, requiredFormat: String?): String {
    //yyyy-MM-dd hh:mm:ss
    var spf = SimpleDateFormat(foundFormat)
    var newDate: Date? = null
    try {
        newDate = spf.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    spf = SimpleDateFormat(requiredFormat)
    var returnValue = ""
    if (newDate != null) {
        returnValue = spf.format(newDate)
    }
    return returnValue
}

fun clearPrefersLogout(context: Context) {
    val prefersPublic = PrefersPublic(context)
    val prefersPrivate = PrefersPrivate(context)
    val perfersKYC = PrefersKYC(context)

    prefersPublic.setString(PrefersPublic.UserLoggedIN, "false")
    prefersPrivate.clearPrefers(context)
    perfersKYC.clearPrefersKYC(context)

    Toast.makeText(context, Constants.SESSION_EXPIRED_MSG, Toast.LENGTH_SHORT).show()
}

