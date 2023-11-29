package com.android.paging_3_compose.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T> Context.openActivity(extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extras()
    startActivity(intent)
}

inline fun <reified T> Context.openActivityWithFinish(extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extras()
    startActivity(intent)
    (this as Activity).finish()
}