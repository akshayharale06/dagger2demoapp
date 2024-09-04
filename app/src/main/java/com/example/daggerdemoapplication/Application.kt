package com.example.daggerdemoapplication

import android.app.Application

class Application: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }
}