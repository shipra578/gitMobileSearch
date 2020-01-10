package com.shipra.android.gitmobilesearch.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shipra.android.gitmobilesearch.dagger.App
import com.shipra.android.gitmobilesearch.model.Repo
import com.shipra.android.gitmobilesearch.repository.ApiRepository
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel(app: Application) : AndroidViewModel(app) {


    init {
        App.getComponent()?.inject(this)
    }

    @Inject
    lateinit var mRepo: ApiRepository


    fun getAllRepo(inputText: String): Observable<Repo> {

        return mRepo.getAllRepos(inputText)

    }
}