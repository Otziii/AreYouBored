package com.jorfald.areyoubored

import android.app.Application

class AreYouBoredApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        application = this
    }

    companion object {
        lateinit var application: AreYouBoredApplication
    }
}