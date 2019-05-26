package com.example.jonaschrtest_002.Database

import android.app.Application


class App  : Application()  {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}