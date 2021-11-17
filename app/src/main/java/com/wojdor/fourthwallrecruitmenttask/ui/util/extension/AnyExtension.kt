package com.wojdor.fourthwallrecruitmenttask.ui.util.extension

import android.util.Log
import com.wojdor.fourthwallrecruitmenttask.BuildConfig

fun Any.logD(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(this::class.simpleName, message)
    }
}

fun Any.logE(message: String, throwable: Throwable) {
    if (BuildConfig.DEBUG) {
        Log.e(this::class.simpleName, message, throwable)
    }
}