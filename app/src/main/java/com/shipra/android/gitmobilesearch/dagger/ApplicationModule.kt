package com.shipra.android.gitmobilesearch.dagger

import android.app.Application
import dagger.Module

@Module
class ApplicationModule(private val application: Application) {

    /*@Singleton
    @Provides
    fun provideContext(): Context {
        return application
    }*/
}