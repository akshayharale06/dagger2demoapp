package com.example.daggerdemoapplication

import dagger.Component

@Component(modules = [])
interface AppComponent {
    fun inject(activity: MainActivity)
}