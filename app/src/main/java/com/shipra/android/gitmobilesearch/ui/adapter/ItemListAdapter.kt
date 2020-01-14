package com.shipra.android.gitmobilesearch.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.ui.adapter.ItemListAdapter.ItemViewHolder

class ItemListAdapter(private val productList: MutableList<ItemsPojo>?, private val mContext: Context) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_list_adapter_layout, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val (_, name, full_name, owner, watcher_count) = productList!![position]
        val avatar_url = owner.avatar_url
        if (avatar_url != null) {
            Glide.with(mContext).load(avatar_url).into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {

                    holder.mItemImage!!.background = resource
                }

            })
        }
        holder.mRepoFullName!!.text = full_name
        holder.mRepoName!!.text = name
        holder.mWatcherCount!!.setText(watcher_count)
        holder.mWatcherCount!!.setText(watcher_count)
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    fun setAll(aproductList: MutableList<ItemsPojo?>?) {
        if (aproductList != null) {
            productList!!.addAll(aproductList as Collection<ItemsPojo>)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.imageView)
        var mItemImage: ImageView? = null
        @BindView(R.id.name)
        var mRepoName: TextView? = null
        @BindView(R.id.fullname)
        var mRepoFullName: TextView? = null
        @BindView(R.id.watcher_count)
        var mWatcherCount: TextView? = null
        @BindView(R.id.commit_count)
        var mCommitCount: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }
    }

}