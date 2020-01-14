package com.shipra.android.gitmobilesearch.api

import com.shipra.android.gitmobilesearch.model.Contributors
import com.shipra.android.gitmobilesearch.model.Repo
import com.shipra.android.gitmobilesearch.model.Repositories
import io.reactivex.Observable
import io.reactivex.ObservableSource
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitApiService {

    @GET("/search/repositories?sort=watchers_count&order=desc")
    fun searchRepositories(@Query("q") userName: String?): Observable<Repo>

    @GET
    fun getRepositories(@Url reposUrl: String): Observable<List<Repositories>>

    @GET
    fun getContributors(@Url contributorsUrl: String): Observable<List<Contributors>>

}