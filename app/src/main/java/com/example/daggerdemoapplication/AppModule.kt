package com.example.daggerdemoapplication

import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class AppModule {
    @Provides
    fun provideDogFactRepository(apiService: ApiService): DogFactRepository{
        return DogFactRepository(apiService)
    }
}