package com.shipra.android.gitmobilesearch.ui.adapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.model.Repositories
import com.shipra.android.gitmobilesearch.ui.ListItemClickListener
import com.shipra.android.gitmobilesearch.ui.RepoDetailActivity
import com.shipra.android.gitmobilesearch.util.Constants

class ItemDetailActivity : AppCompatActivity() , ListItemClickListener {

    var listOfRepos: ArrayList<Repositories> = arrayListOf()
    lateinit var description: String
    lateinit var projectLink : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        val bundle = intent?.getBundleExtra(Constants.ITEM_LIST_BUNDLE)

        val itemPojo = bundle?.getParcelable<ItemsPojo>(Constants.ITEM_OBJECT)
        description = intent?.getStringExtra(Constants.KEY_DESCRIPTION)!!
        projectLink = intent.getStringExtra(Constants.KEY_PROJECT_LINK)!!
        listOfRepos = itemPojo?.repositories as ArrayList<Repositories>
    }

    override fun onListItemClicked(view: View?, position: Int) {

        val intent = Intent(applicationContext, RepoDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_REPO_OBJECT, listOfRepos[position])
        intent.putExtra(Constants.REPO_LIST_BUNDLE, bundle)
        intent.putExtra(Constants.KEY_PROJECT_LINK,projectLink)
        intent.putExtra(Constants.KEY_DESCRIPTION,description)
        startActivity(intent)
    }
}
