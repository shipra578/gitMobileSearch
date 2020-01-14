package com.shipra.android.gitmobilesearch.repository

import android.app.Application
import android.util.Log
import com.shipra.android.gitmobilesearch.api.GitApiService
import com.shipra.android.gitmobilesearch.dagger.GitDaggerModule
import com.shipra.android.gitmobilesearch.dagger.GitDaggerModule_ProvideGitApiServiceFactory
import com.shipra.android.gitmobilesearch.liveData.CustomLivedata
import com.shipra.android.gitmobilesearch.model.*
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRepository @Inject constructor(app: Application) {

    @JvmField
    var gitApiService: GitApiService = GitDaggerModule_ProvideGitApiServiceFactory.create(GitDaggerModule()).get()

    @JvmField
    var mDataBase = GitProjectDatabase.getInstance(app)

    lateinit var mLivedata: CustomLivedata<List<ItemsPojo>>

    @JvmField
    var mDao: GitDao

    @JvmField
    var app: Application? = app


    init {
        mDao = mDataBase?.gitDatabaseDao()!!
    }

    companion object {

        val TAG: String = "ApiRepository"

        @JvmField
        var mInstance: ApiRepository? = null


        fun getInstance(app: Application): ApiRepository {

            if (mInstance == null) {
                mInstance = ApiRepository(app)
            }

            return mInstance as ApiRepository
        }
    }


    fun getAllRepos(searchText: String): CustomLivedata<List<ItemsPojo>> {


        mLivedata = CustomLivedata()

        var itemID = 0
        var RepoId = 0

        val subscribeWith = gitApiService.searchRepositories(searchText).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).flatMap(object : Function<Repo, ObservableSource<List<ItemsPojo>>> {

            override fun apply(t: Repo): ObservableSource<List<ItemsPojo>> {
                return Observable.fromArray(t.items)
            }
        }

        ).flatMapIterable {

            it
        }.flatMap(object : Function<ItemsPojo?, ObservableSource<List<Repositories>>?> {

            override fun apply(t: ItemsPojo): ObservableSource<List<Repositories>> {
                Log.e(TAG, "Items details: ${t.full_name} and Watcher count : ${t.watcher_count}")

                if (!mDao.checkIfRepoPresent(t.id)) {
                    mDao.insertItemsPojo(t)
                }
                itemID = t.id
                return gitApiService.getRepositories(t.owner.repos_url)
            }
        }).flatMapIterable {
            it

        }.flatMap(object : Function<Repositories?, ObservableSource<List<Contributors>>?> {

            override fun apply(t: Repositories): ObservableSource<List<Contributors>> {

                Log.e(TAG, "Repository details: ${t.contributors_url} and RepoId : ${t.repoId}")
                t.item_id = itemID
                if (!mDao.checkIfItemPresent(t.repoId)) {
                    mDao.insertRepositories(t)

                }

                RepoId = t.repoId
                return gitApiService.getContributors(t.contributors_url)
            }
        })?.flatMapIterable {
            it

        }?.doOnError {
            Log.e(TAG, " Error on flatmapIterable:  ${it.message}")

        }?.onErrorResumeNext(Observable.empty())?.flatMap(object : Function<Contributors?, ObservableSource<List<ItemsPojo>>?> {

            override fun apply(t: Contributors): ObservableSource<List<ItemsPojo>>? {

                val hasRelation = HasRelation(RepoId, t.id)
                val tid = t.id
                Log.i(TAG, "RepoID $RepoId , ContributorId: $tid")
                if (!mDao.checkIfContributorPresent(tid)) {
                    mDao.insertContributors(t)
                }

                if (!mDao.checkIfRelationPresent(RepoId)) {
                    mDao.insertHasRelationship(hasRelation)
                }
                return Observable.fromArray(mDao.loadAllRepositories())
            }
        })?.subscribeWith(object : DisposableObserver<List<ItemsPojo>?>() {

            override fun onComplete() {
                Log.i(TAG, "**********completed")
            }

            override fun onNext(t: List<ItemsPojo>) {

                mLivedata.postValue(t)
            }

            override fun onError(e: Throwable) {

                Log.e(TAG, "**********error ${e.message}")
            }
        })
        return mLivedata
    }

}


