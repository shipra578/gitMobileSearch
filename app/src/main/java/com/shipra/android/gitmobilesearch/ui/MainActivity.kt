package com.shipra.android.gitmobilesearch.ui

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
import com.shipra.android.gitmobilesearch.ui.adapter.ItemListAdapter
import com.shipra.android.gitmobilesearch.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,ListItemClickListener, SearchView.OnQueryTextListener{

    companion object {
        val TAG = "MainActivity"
    }


    lateinit var mRecyclerView: RecyclerView

    lateinit var mSearchView : SearchView
    lateinit var adapter: ItemListAdapter

    var itemsList: ArrayList<ItemsPojo> = arrayListOf()
    var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = repo_recycler_view
        mSearchView = search_repo

        mSearchView.setOnQueryTextListener(this)
        val manager = LinearLayoutManager(applicationContext)
        manager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.setLayoutManager(manager)
        adapter = ItemListAdapter(itemsList, this)
        mRecyclerView.adapter = adapter
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)
        itemsList.clear()
        mViewModel?.getAllRepo("Shipra")?.observe(this, Observer {
            for (item in it) {
                Log.e(TAG, item.full_name)
                itemsList.add(item)
            }
            adapter.notifyDataSetChanged()
        })
    }

    override fun onListItemClicked(view: View, position: Int, obj: Any) {


    }

    override fun onQueryTextSubmit(p0: String?): Boolean {

        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        adapter.filter.filter(p0)
        return false
    }
}