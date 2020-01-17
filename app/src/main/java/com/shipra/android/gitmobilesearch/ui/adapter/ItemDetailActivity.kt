package com.shipra.android.gitmobilesearch.ui.adapter

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.model.Repositories
import com.shipra.android.gitmobilesearch.ui.ListItemClickListener
import com.shipra.android.gitmobilesearch.ui.RepoDetailActivity
import com.shipra.android.gitmobilesearch.util.Constants
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity(), ListItemClickListener {

    var listOfRepos: ArrayList<Repositories> = arrayListOf()
    lateinit var description: String
    lateinit var projectLink: String

    lateinit var mRecycler: RecyclerView
    lateinit var adapter: ItemDetailAdapter

    lateinit var imageOwner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        val bundle = intent?.getBundleExtra(Constants.ITEM_LIST_BUNDLE)
        mRecycler = repo_recycler_view
        var avatar_url: String
        if (bundle != null) {
            val itemPojo = bundle?.getParcelable<ItemsPojo>(Constants.ITEM_OBJECT)
            listOfRepos = itemPojo?.repositories as ArrayList<Repositories>

            avatar_url = itemPojo.owner.avatar_url
            if (avatar_url != null) {
                Glide.with(this).load(avatar_url).into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                        imageOwner.background = resource
                    }
                })
            }
        }
        description = intent?.getStringExtra(Constants.KEY_DESCRIPTION)!!
        projectLink = intent.getStringExtra(Constants.KEY_PROJECT_LINK)!!

        val manager = LinearLayoutManager(applicationContext)
        manager.orientation = LinearLayoutManager.VERTICAL
        mRecycler.setLayoutManager(manager)
        adapter = ItemDetailAdapter(listOfRepos, this)
        mRecycler.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onListItemClicked(view: View?, position: Int) {

        val intent = Intent(applicationContext, RepoDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_REPO_OBJECT, listOfRepos[position])
        intent.putExtra(Constants.REPO_LIST_BUNDLE, bundle)
        intent.putExtra(Constants.KEY_PROJECT_LINK, projectLink)
        intent.putExtra(Constants.KEY_DESCRIPTION, description)
        startActivity(intent)
    }
}
