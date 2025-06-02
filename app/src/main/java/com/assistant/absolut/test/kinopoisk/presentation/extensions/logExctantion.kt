package com.assistant.absolut.test.kinopoisk.presentation.extensions

import android.util.Log
import com.squareup.leakcanary.core.BuildConfig

fun log(msg: String) {
    if(BuildConfig.DEBUG) {
        runCatching {
            Log.d("!!!", msg)
        }.getOrElse {
            println(msg)
        }
    }
}