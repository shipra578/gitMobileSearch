package com.shipra.android.gitmobilesearch.dagger

import com.shipra.android.gitmobilesearch.api.GitApiService
import com.shipra.android.gitmobilesearch.api.GitRetrofitCreator
import dagger.Module
import dagger.Provides

@Module
class GitDaggerModule {

    @Provides
    fun providesGitApiService() : GitApiService?{
        return GitRetrofitCreator.newApiService()
    }

}