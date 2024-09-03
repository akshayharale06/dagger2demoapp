package com.example.daggerdemoapplication

import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}