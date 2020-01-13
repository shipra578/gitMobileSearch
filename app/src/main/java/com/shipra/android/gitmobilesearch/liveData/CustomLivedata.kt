package com.shipra.android.gitmobilesearch.liveData

import androidx.lifecycle.MutableLiveData

class CustomLivedata<T> : MutableLiveData<T>() {


    override fun postValue(value: T) {
        super.postValue(value)
    }

}