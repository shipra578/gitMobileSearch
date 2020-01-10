package com.shipra.android.gitmobilesearch.repository

import android.app.Application
import com.shipra.android.gitmobilesearch.api.GitApiService
import com.shipra.android.gitmobilesearch.model.Repo
import io.reactivex.Observable
import javax.inject.Inject

class ApiRepository @Inject constructor(app: Application) {

    @Inject
    lateinit var gitApiService: GitApiService

    fun getAllRepos(searchText: String): Observable<Repo> {

        return gitApiService.searchRepositories(searchText);
    }
}