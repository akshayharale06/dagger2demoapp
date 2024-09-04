package com.example.daggerdemoapplication

import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}