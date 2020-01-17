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
import java.util.concurrent.TimeUnit
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

    fun getAllReposFromDB(searchValue: String): List<ItemsPojo> {

        var lisItems: List<ItemsPojo> = mDao.loadAllItemsRepo(searchValue)
        Log.e(TAG, "List item size : ${lisItems.size} ")
        for (item in lisItems) {

            val repoList = mDao.getRepositoriesFromItemId(item.id)

            for (repoItem in repoList) {
                val contributorList = mDao.getAllContributors1(repoItem.repoId)
                //Log.e(TAG, "contributor list size: ${contributorList.size}")
                repoItem.contributors = contributorList
            }
            item.repositories = repoList
        }
        return lisItems
    }


    /*  fun getAllRepos(searchText: String): CustomLivedata<List<ItemsPojo>> {


          mLivedata = CustomLivedata()
          val repo = mDao.checkIfRepoExists(searchText)
          if (repo?.searchText != null) {
              mLivedata.postValue(getAllReposFromDB())
              return mLivedata
          }

          var itemID = 0
          var RepoId = 0
          var itemList: List<ItemsPojo> = arrayListOf()
          val subscribeWith = gitApiService.searchRepositories(searchText).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).flatMap(object : Function<Repo, ObservableSource<List<ItemsPojo>>> {

              override fun apply(t: Repo): ObservableSource<List<ItemsPojo>> {
                  t.searchText = searchText
                  mDao.insertRepo(t)
                  return Observable.fromArray(t.items)
              }
          }

          ).filter {
              it !=null && !it.isEmpty()
          }?.flatMapIterable {
              Log.e(TAG,"********************************Items Pojo insertion started**************************************************************************************")
              itemList = it
              it

          }?.flatMap(object : Function<ItemsPojo?, ObservableSource<List<Repositories>>?> {

              override fun apply(t: ItemsPojo): ObservableSource<List<Repositories>> {

                  if (mDao.checkIfRepoPresent(t.id) == null) {
                      mDao.insertItemsPojo(t)
                  }

                  itemID = t.id
                  return gitApiService.getRepositories(t.owner.repos_url)
              }
          })?.filter {
              it !=null && !it.isEmpty()
          }?.flatMapIterable {
              Log.e(TAG,"********************************Repo insertion started**************************************************************************************")
              it

          }?.flatMap(object : Function<Repositories?, ObservableSource<List<Contributors>>?> {

              override fun apply(t: Repositories): ObservableSource<List<Contributors>> {
                  t.item_id = itemID
                  if (mDao.checkIfItemPresent(t.repoId) == null) {
                      mDao.insertRepositories(t)
                  }

                 // printAllReposAfterInsertion(mDao.selectAllRepositories() as ArrayList<Repositories>)
                  RepoId = t.repoId
                  Log.e(TAG,"contributors url : ${t.contributors_url}")
                  return gitApiService.getContributors(t.contributors_url)
              }
          })?.onErrorResumeNext(Observable.empty())?.filter {
              Log.e(TAG, "it value $it")
              it !=null && !it.isEmpty()
          }?.onErrorResumeNext(Observable.empty())?.flatMapIterable {
              Log.e(TAG,"********************************Contributor insertion started**************************************************************************************")
              it

          }?.doOnError {
              Log.e(TAG, " Error on flatmapIterable:  ${it.message}")

          }?.onErrorResumeNext(Observable.empty())?.flatMap(object : Function<Contributors?, ObservableSource<List<ItemsPojo>>?> {

              override fun apply(t: Contributors): ObservableSource<List<ItemsPojo>>? {

                  val hasRelation = ContributorRepoRelation(0,t.id,RepoId )
                  val tid = t.id
                  Log.e(TAG, "RepoID $RepoId , ContributorId: $tid")

                 // val contributor = mDao.checkIfContributorPresent(tid)
                  val repository = mDao.checkIfRepoPresent(RepoId)

                  mDao.insertContributors(t)

                  if (mDao.checkIfContributorPresent(tid) != null && repository != null) {
                      Log.e(TAG,"inserting into has relationship table")
                      mDao.insertContRepo(hasRelation)
                  }
                  return Observable.fromArray(getAllReposFromDB())
              }
          })?.subscribeWith(object : DisposableObserver<List<ItemsPojo>?>() {

              override fun onComplete() {
                  mLivedata.postValue(itemList)
                  Log.i(TAG, "**********completed")
              }

              override fun onNext(t: List<ItemsPojo>) {
                  itemList = t

              }

              override fun onError(e: Throwable) {

                  Log.e(TAG, "**********error ${e.message}")
              }
          })
          return mLivedata
      }*/


    fun getAllRepos(asearchText: String): Observable<List<ItemsPojo>>? {


        var itemID = 0
        var RepoId = 0

        return gitApiService.searchRepositories(asearchText).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).flatMap(object : Function<Repo, ObservableSource<List<ItemsPojo>>> {

            override fun apply(t: Repo): ObservableSource<List<ItemsPojo>> {
                t.searchText = asearchText
                mDao.insertRepo(t)
                return Observable.fromArray(t.items)
            }
        }

        ).filter {
            it != null && !it.isEmpty()
        }?.flatMapIterable {
            it

        }?.flatMap(object : Function<ItemsPojo?, ObservableSource<List<Repositories>>?> {

            override fun apply(t: ItemsPojo): ObservableSource<List<Repositories>> {

                if (mDao.checkIfRepoPresent(t.id) == null) {
                    mDao.insertItemsPojo(t)
                }

                itemID = t.id
                return gitApiService.getRepositories(t.owner.repos_url)
            }
        })?.filter {
            it != null && !it.isEmpty()
        }?.flatMapIterable {
            it

        }?.flatMap(object : Function<Repositories?, ObservableSource<List<Contributors>>?> {

            override fun apply(t: Repositories): ObservableSource<List<Contributors>> {
                t.item_id = itemID
                // listRepositories.add(t)
                if (mDao.checkIfItemPresent(t.repoId) == null) {
                    mDao.insertRepositories(t)
                }

                // printAllReposAfterInsertion(mDao.selectAllRepositories() as ArrayList<Repositories>)
                RepoId = t.repoId
                return gitApiService.getContributors(t.contributors_url)
            }
        })?.onErrorResumeNext(Observable.empty())?.filter {
            Log.e(TAG, "it value $it")
            it != null && !it.isEmpty()
        }?.onErrorResumeNext(Observable.empty())?.flatMapIterable {
            it

        }?.doOnError {
            Log.e(TAG, " Error on flatmapIterable:  ${it.message}")

        }?.onErrorResumeNext(Observable.empty())
                ?.flatMap(object : Function<Contributors?, ObservableSource<List<ItemsPojo>>?> {

                    override fun apply(t: Contributors): ObservableSource<List<ItemsPojo>>? {

                        if (t != null) {
                            val hasRelation = ContributorRepoRelation(0, t.id, RepoId)
                            val tid = t.id

                            // val contributor = mDao.checkIfContributorPresent(tid)
                            val repository = mDao.checkIfRepoPresent(RepoId)
                            val contributor = mDao.checkIfContributorPresent(tid)

                            if (contributor != null) {
                                mDao.insertContributors(t)
                            }

                            if (mDao.checkIfContributorPresent(tid) != null && repository != null) {
                                mDao.insertContRepo(hasRelation)
                            }
                        }
                        return Observable.fromArray(getAllReposFromDB(asearchText))
                    }
                })
    }

    fun getAllRepos(searchText: Observable<String>): CustomLivedata<List<ItemsPojo>> {

        var itemList: List<ItemsPojo> = arrayListOf()
        mLivedata = CustomLivedata()


        searchText.debounce(10, TimeUnit.SECONDS)?.filter { text ->
            if (text.isEmpty()) {
                false
            } else {
                true
            }
        }?.switchMap(object : Function<String?, ObservableSource<List<ItemsPojo>>?> {

            override fun apply(t: String): ObservableSource<List<ItemsPojo>>? {


                val repo = mDao.checkIfRepoExists(t)
                if (repo?.searchText != null) {
                    return Observable.fromArray(getAllReposFromDB(t))
                }
                return getAllRepos(t)
            }
        })?.subscribeWith(object : DisposableObserver<List<ItemsPojo>?>() {

            override fun onComplete() {
                mLivedata.postValue(itemList)
                Log.i(TAG, "**********completed")
            }

            override fun onNext(t: List<ItemsPojo>) {
                itemList = t
                mLivedata.postValue(itemList)
            }

            override fun onError(e: Throwable) {

                Log.e(TAG, "**********error ${e.message}")
            }
        })
        return mLivedata
    }
}


