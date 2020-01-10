package com.shipra.android.gitmobilesearch.dagger

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }


    companion object {
        private  var component: CustomDaggerComponent? = null

        fun getComponent(): CustomDaggerComponent? {
            if (component == null) {

                component = DaggerCustomDaggerComponent.builder().application(application = Application()).build()
            }
            return component
        }
    }
}