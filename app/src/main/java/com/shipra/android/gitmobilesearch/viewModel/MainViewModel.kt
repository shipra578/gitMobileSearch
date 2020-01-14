package com.shipra.android.gitmobilesearch.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shipra.android.gitmobilesearch.liveData.CustomLivedata
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.repository.ApiRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {


    @JvmField
    var mRepo: ApiRepository


    init {
        mRepo = ApiRepository.getInstance(app)
    }

    fun getAllRepo(inputText: String): CustomLivedata<List<ItemsPojo>>? {

        return mRepo.getAllRepos(inputText)


    }
}