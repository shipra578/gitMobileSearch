package com.shipra.android.gitmobilesearch.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.ui.adapter.ItemDetailActivity
import com.shipra.android.gitmobilesearch.ui.adapter.ItemListAdapter
import com.shipra.android.gitmobilesearch.util.Constants
import com.shipra.android.gitmobilesearch.util.GetRxObservableFromView
import com.shipra.android.gitmobilesearch.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ListItemClickListener {

    companion object {
        val TAG = "MainActivity"
    }


    lateinit var mRecyclerView: RecyclerView

    lateinit var mSearchView: SearchView
    lateinit var adapter: ItemListAdapter

    var itemsList: ArrayList<ItemsPojo> = arrayListOf()
    var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = item_recycler_view
        mSearchView = search_repo

        //mSearchView.setOnQueryTextListener(this)
        val manager = LinearLayoutManager(applicationContext)
        manager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.setLayoutManager(manager)
        adapter = ItemListAdapter(itemsList, this)
        mRecyclerView.adapter = adapter
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)

        registerForSearchResult()

    }

    fun registerForSearchResult() {

        mViewModel?.getAllRepo(GetRxObservableFromView.fromView(mSearchView))?.observe(this, Observer {
            itemsList.clear()
            for (item in it) {
                Log.e(TAG, item.full_name)
                itemsList.add(item)
            }
            adapter.notifyDataSetChanged()
        })
    }

    override fun onListItemClicked(view: View?, position: Int) {

        var intent = Intent(applicationContext, ItemDetailActivity::class.java)

        var bundle = Bundle()

        bundle.putParcelable(Constants.ITEM_OBJECT, itemsList[position])

        intent.putExtra(Constants.ITEM_LIST_BUNDLE, bundle)
        var desc = itemsList[position].description
        if (desc == null) {
            desc = ""
        }
        intent.putExtra(Constants.KEY_DESCRIPTION, desc)
        intent.putExtra(Constants.KEY_PROJECT_LINK, itemsList[position].html_url)
        startActivity(intent)
    }
}