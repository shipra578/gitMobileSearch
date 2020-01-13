package com.shipra.android.gitmobilesearch.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shipra.android.gitmobilesearch.dagger.App
import com.shipra.android.gitmobilesearch.liveData.CustomLivedata
import com.shipra.android.gitmobilesearch.model.Repo
import com.shipra.android.gitmobilesearch.repository.ApiRepository
import javax.inject.Inject

class MainViewModel(app: Application) : AndroidViewModel(app) {


    @JvmField
     var mRepo: ApiRepository


    init {
        mRepo = ApiRepository.getInstance(app)
    }

    fun getAllRepo(inputText: String): CustomLivedata<Repo>? {

            return mRepo.getAllRepos(inputText)


    }
}