package com.shipra.android.gitmobilesearch.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.Contributors
import com.shipra.android.gitmobilesearch.model.Repositories
import com.shipra.android.gitmobilesearch.ui.adapter.ContributorAdapter
import com.shipra.android.gitmobilesearch.util.Constants
import kotlinx.android.synthetic.main.activity_repo_detail.*

class RepoDetailActivity : AppCompatActivity() {

    lateinit var description: String
    lateinit var projectLink: String
    lateinit var ownerImage: ImageView
    lateinit var name: TextView
    lateinit var descriptionView: TextView
    lateinit var projectLinkView: TextView
    lateinit var adapter: ContributorAdapter
    lateinit var contRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        ownerImage = owner_image
        descriptionView = description_value
        projectLinkView = project_url
        contRecycler = contributor_list
        var bundle = intent?.getBundleExtra(Constants.REPO_LIST_BUNDLE)
        var itemPojo = bundle?.getParcelable<Repositories>(Constants.KEY_REPO_OBJECT)
        description = intent?.getStringExtra(Constants.KEY_DESCRIPTION)!!
        projectLink = intent.getStringExtra(Constants.KEY_PROJECT_LINK)!!

        descriptionView.setText(description)
        projectLinkView.setText(projectLink)


        projectLinkView.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, WebViewActivity::class.java)
            intent.putExtra(Constants.KEY_WEB_URL, projectLink)
            startActivity(intent)
        })
        var avatar_url: String
        avatar_url = itemPojo?.avatar_url!!

        if (avatar_url != null && !avatar_url.isEmpty()) {
            Glide.with(this).load(avatar_url).into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    ownerImage.background = resource
                }
            })
        }

        val manager = GridLayoutManager(this, 4)
        manager.orientation = GridLayoutManager.HORIZONTAL
        contRecycler.setLayoutManager(manager)
        adapter = ContributorAdapter(itemPojo.contributors as ArrayList<Contributors>, this)
        contRecycler.adapter = adapter
    }
}