package com.kmpshowcase.client

import android.app.Application

class KMPShowcaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: KMPShowcaseApplication
            private set
    }
}
