package com.shipra.android.gitmobilesearch.dagger

import android.app.Application
import androidx.room.Room
import com.shipra.android.gitmobilesearch.api.GitApiService
import com.shipra.android.gitmobilesearch.api.GitRetrofitCreator
import com.shipra.android.gitmobilesearch.model.GitDao
import com.shipra.android.gitmobilesearch.model.GitProjectDatabase
import com.shipra.android.gitmobilesearch.repository.ApiRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GitDaggerModule {


/*
    @Provides
    @Singleton
    fun provideGitProjectDatabase(application: Application): GitProjectDatabase {
        return Room.databaseBuilder(application, GitProjectDatabase::class.java, GitProjectDatabase.DATABASE_NAME).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideGitDao(db: GitProjectDatabase): GitDao {
        return db.gitDatabaseDao()
    }
*/

   /* @Provides
    @Singleton
    fun provideApiRepository(application: Application): ApiRepository {
        return ApiRepository(application)
    }*/

    @Provides
    fun provideGitApiService() : GitApiService{
        return GitRetrofitCreator.newApiService()
    }



}
