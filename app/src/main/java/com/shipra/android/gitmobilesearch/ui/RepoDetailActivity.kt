package com.shipra.android.gitmobilesearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.util.Constants

class RepoDetailActivity : AppCompatActivity() {

    lateinit var description: String
    lateinit var projectLink : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        var bundle = intent?.getBundleExtra(Constants.REPO_LIST_BUNDLE)
        var itemPojo = bundle?.getParcelable<ItemsPojo>(Constants.KEY_REPO_OBJECT)
        description = intent?.getStringExtra(Constants.KEY_DESCRIPTION)!!
        projectLink = intent.getStringExtra(Constants.KEY_PROJECT_LINK)!!
    }
}