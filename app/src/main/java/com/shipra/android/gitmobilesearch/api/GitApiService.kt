package com.shipra.android.gitmobilesearch.api

import com.shipra.android.gitmobilesearch.model.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApiService {

    @GET("/search/repositories?q={userName}&sort=watchers_count&order=desc")
    fun searchRepositories(@Path("userName") userName: String?): Observable<Repo?>?

}