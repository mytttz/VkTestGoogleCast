package com.example.vktestgooglecast

import org.koin.core.context.startKoin

class MyApplication : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(castModule)
        }
    }
}