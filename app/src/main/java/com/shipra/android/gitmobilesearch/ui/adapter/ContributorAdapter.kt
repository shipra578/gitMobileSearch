package com.shipra.android.gitmobilesearch.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.Contributors
import kotlinx.android.synthetic.main.contributor_adapter_layout.view.*

class ContributorAdapter(private val contList: ArrayList<Contributors>?, mContext: Context) : RecyclerView.Adapter<ContributorAdapter.ContributorHolder>() {


    var context = mContext
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.contributor_adapter_layout, parent, false)

        return ContributorHolder(v)
    }

    override fun onBindViewHolder(holder: ContributorHolder, position: Int) {

        val contName = contList!![position].name
        holder.mContributorName!!.text = contName
        val avatar_url = contList!![position].avatar_url
        if (avatar_url != null) {
            Glide.with(context).load(avatar_url).into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {

                    holder.mContributorImage!!.background = resource
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return contList?.size ?: 0
    }


    class ContributorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mContributorName: TextView? = itemView.contributor_name
        var mContributorImage: ImageView = itemView.contributor_avtar
    }

}