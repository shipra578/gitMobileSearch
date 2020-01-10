package com.shipra.android.gitmobilesearch.dagger

import android.app.Application
import com.shipra.android.gitmobilesearch.repository.ApiRepository
import com.shipra.android.gitmobilesearch.viewModel.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitDaggerModule::class, ApplicationModule::class])
interface CustomDaggerComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CustomDaggerComponent


    }

    //fun getApplication(app : Application)

    fun inject(mainViewModel: MainViewModel)

    fun getApiRepository(app: Application)
    //fun inject(repo : ApiRepository)



}