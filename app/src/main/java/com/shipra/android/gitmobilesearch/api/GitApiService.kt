package com.shipra.android.gitmobilesearch.api

import com.shipra.android.gitmobilesearch.model.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApiService {

    @GET("/search/repositories?sort=watchers_count&order=desc")
    fun searchRepositories(@Query("q") userName: String?): Observable<Repo>

}