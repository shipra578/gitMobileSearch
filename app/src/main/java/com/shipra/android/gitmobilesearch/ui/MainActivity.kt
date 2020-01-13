package com.shipra.android.gitmobilesearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.dagger.App
import com.shipra.android.gitmobilesearch.viewModel.MainViewModel
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {


    var mViewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)
        mViewModel?.getAllRepo("Shipra")
    }
}