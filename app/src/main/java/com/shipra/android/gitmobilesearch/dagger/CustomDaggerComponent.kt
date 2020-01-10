package com.shipra.android.gitmobilesearch.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitDaggerModule::class, ApplicationModule::class])
interface CustomDaggerComponent {


    fun inject(mainAppInstance: App?)

    /*@Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): CustomDaggerComponent
    }*/

}