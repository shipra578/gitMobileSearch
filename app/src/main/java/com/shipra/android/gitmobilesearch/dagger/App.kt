package com.shipra.android.gitmobilesearch.dagger

import android.app.Application


class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }


    companion object {
        var comp: CustomDaggerComponent? = null

        fun getComponent(): CustomDaggerComponent? {
            if (comp == null) {

                comp = DaggerCustomDaggerComponent.builder().application(application = Application()).build()
            }
            return comp
        }
    }
}