package com.shipra.android.gitmobilesearch.dagger

import android.app.Application

class App : Application() {

    private var component: CustomDaggerComponent? = null

    override fun onCreate() {
        super.onCreate()
        getComponent()!!.inject(this)
    }

    fun getComponent(): CustomDaggerComponent? {
        if (component == null) {

            component = DaggerCustomDaggerComponent.builder().build()
        }
        return component
    }
}