package com.android.paging_3_compose.app

import android.app.Application
import android.content.Context
import com.android.paging_3_compose.utils.SharedPreferenceUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject lateinit var preference: SharedPreferenceUtils

    override fun onCreate() {
        super.onCreate()
        myApp = this
        myApplication = this
    }

    companion object {

        lateinit var myApp: App

        @JvmStatic
        fun getApp() = myApp

        var myApplication: Context? = null
            private set

        @JvmStatic
        val staticContext: Context
            get() = myApplication!!.applicationContext
    }
}

fun getPref(): SharedPreferenceUtils {
    return App.getApp().preference
}