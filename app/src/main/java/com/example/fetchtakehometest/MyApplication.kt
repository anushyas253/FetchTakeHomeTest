package com.example.fetchtakehometest

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@SuppressWarnings
@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }

}