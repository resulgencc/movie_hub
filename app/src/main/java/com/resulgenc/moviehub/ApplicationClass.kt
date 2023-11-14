package com.resulgenc.moviehub

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class ApplicationClass : Application() {


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int, tag: String?, message: String, t: Throwable?
                ) {
                    super.log(priority, "console_$tag", message, t)
                }
            })
        }
    }
}