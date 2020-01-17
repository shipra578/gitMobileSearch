package com.shipra.android.gitmobilesearch.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.model.Repositories
import com.shipra.android.gitmobilesearch.util.Constants
import kotlinx.android.synthetic.main.activity_repo_detail.*

class RepoDetailActivity : AppCompatActivity() {

    lateinit var description: String
    lateinit var projectLink : String
    lateinit var ownerImage : ImageView
    lateinit var name: TextView
    lateinit var descriptionView:  TextView
    lateinit var projectLinkView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        ownerImage = owner_image
        descriptionView = description_value
        projectLinkView = project_url
        var bundle = intent?.getBundleExtra(Constants.REPO_LIST_BUNDLE)
        var itemPojo = bundle?.getParcelable<Repositories>(Constants.KEY_REPO_OBJECT)
        description = intent?.getStringExtra(Constants.KEY_DESCRIPTION)!!
        projectLink = intent.getStringExtra(Constants.KEY_PROJECT_LINK)!!

        descriptionView.setText(description)
        projectLinkView.setText(projectLink)
        var avatar_url: String
        avatar_url = itemPojo?.owner?.avatar_url!!

        if (avatar_url != null) {
            Glide.with(this).load(avatar_url).into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    ownerImage.background = resource
                }
            })
        }
    }
}