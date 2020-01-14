package com.shipra.android.gitmobilesearch.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.ui.adapter.ItemListAdapter
import com.shipra.android.gitmobilesearch.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
         val TAG = "MainActivity"
    }

    @JvmField
    @BindView(R.id.repo_recycler_view)
    var mRecyclerView: RecyclerView? = null

    lateinit var adapter : ItemListAdapter

    var itemsList: ArrayList<ItemsPojo> = ArrayList()
    var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        adapter = ItemListAdapter(itemsList,this)

        val manager = LinearLayoutManager(applicationContext)
        manager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView?.setLayoutManager(manager)
        mRecyclerView?.adapter = adapter

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)
        mViewModel?.getAllRepo("Shipra")?.observe(this, Observer {
            for (item in it) {
                Log.e(TAG, item.name)
                itemsList.add(item)
                adapter.notifyDataSetChanged()
            }
        })
    }
}