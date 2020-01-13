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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRepository @Inject constructor(app: Application) {

    @JvmField
    var gitApiService: GitApiService = GitDaggerModule_ProvideGitApiServiceFactory.create(GitDaggerModule()).get()

    @JvmField
    var mDataBase = GitProjectDatabase.getInstance(app)

    lateinit var mLivedata: CustomLivedata<Repo>

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


    fun getAllRepos(searchText: String): CustomLivedata<Repo> {


        mLivedata = CustomLivedata()


        /* val subscribeWith = gitApiService.searchRepositories(searchText).subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribeWith(object : DisposableObserver<Repo>() {

           override fun onComplete() {
               Log.e(TAG, "onComplete")
           }


           override fun onNext(t: Repo) {



               mLivedata.postValue(t)
           }

           override fun onError(e: Throwable) {

               Log.e(TAG, e?.message)
           }
       })*/

        var mRepoObject: Repo
        var itemID: Int = 0
        var RepoId: Int = 0;

        gitApiService.searchRepositories(searchText).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(object : Function<Repo, ObservableSource<List<ItemsPojo>>> {

            override fun apply(t: Repo): ObservableSource<List<ItemsPojo>> {
                mRepoObject = t
                return Observable.fromArray(t.items)
            }
        }

        ).flatMapIterable {

            it
        }.flatMap(object : Function<ItemsPojo, ObservableSource<List<Repositories>>> {

            override fun apply(t: ItemsPojo): ObservableSource<List<Repositories>> {
                mDao.insertItemsPojo(t)
                itemID = t.id
                return gitApiService.getRepositories(t.owner.repos_url)
            }
        }).flatMapIterable {
            it

        }.flatMap(object : Function<Repositories?, ObservableSource<List<Contributors>>> {

            override fun apply(t: Repositories): ObservableSource<List<Contributors>> {

                t.item_id = itemID
                //mDao.insertRepositories(t)

                RepoId = t.repoId
                return gitApiService.getContributors(t.contributors_url)
            }
        }).flatMapIterable {
            it

        }.subscribeWith(object : DisposableObserver<Contributors>() {

            override fun onComplete() {
                Log.i(TAG, "**********completed")
            }

            override fun onNext(t: Contributors) {
                var hasRelation = HasRelation(0, RepoId, t.id)
                // mDao.insertHasRelationship(hasRelation)
                // mDao.insertContributors(t)

            }

            override fun onError(e: Throwable) {

                Log.e(TAG, "**********error ${e.message}")
            }
        })


        /* gitApiService.searchRepositories(searchText).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(object : Function<Repo, ObservableSource<List<ItemsPojo>>> {

             @Throws(Exception::class)
             override fun apply(t: Repo): ObservableSource<List<ItemsPojo>>? {

                 mRepoObject = t
                 return Observable.fromArray(t.items)
             }
         }).flatMapIterable {
             it
         }.flatMap(object : Function<ItemsPojo?, ObservableSource<List<Repositories>>> {

             override fun apply(t: ItemsPojo): ObservableSource<List<Repositories>> {

                 // mDao.insertItemsPojo(t)
                 itemID = t.id
                 return gitApiService.getRepositories(t.owner.repos_url)
             }

         }).flatMapIterable {
             it

         }.flatMap(object : Function<Repositories?, ObservableSource<List<Contributors>>> {

             override fun apply(t: Repositories): ObservableSource<List<Contributors>> {

                 t.item_id = itemID
                 //mDao.insertRepositories(t)

                 RepoId = t.repoId
                 return gitApiService.getContributors(t.contributors_url)
             }
         })?.flatMapIterable {
             it

         }.subscribeWith(object : DisposableObserver<Contributors>() {

             override fun onComplete() {
                 Log.i(TAG, "**********completed")
             }

             override fun onNext(t: Contributors) {
                 var hasRelation = HasRelation(0, RepoId, t.id)
                 // mDao.insertHasRelationship(hasRelation)
                 // mDao.insertContributors(t)

             }

             override fun onError(e: Throwable) {

                 Log.e(TAG, "**********error ${e.message}")
             }
         })*/
        return mLivedata
    }

    fun getContributors(items: List<ItemsPojo>) {


        for (it in items) {

            it.owner.repos_url
        }

    }

}


fun <T> Observable<T>.flatMap(function: (T) -> Unit): Any {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}


