package com.shipra.android.gitmobilesearch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.Repositories
import com.shipra.android.gitmobilesearch.ui.ListItemClickListener
import kotlinx.android.synthetic.main.item_detail_adapter.view.*

class ItemDetailAdapter (private val repoList: ArrayList<Repositories>?, mContext: Context) : RecyclerView.Adapter<ItemDetailAdapter.RepoViewHolder>() {

    var mListener: ListItemClickListener = mContext as ItemDetailActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_adapter, parent, false)

        return RepoViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

        val repoName  = repoList!![position].name

        holder.mRepoLink!!.text = repoName
    }

    override fun getItemCount(): Int {
        return repoList?.size ?: 0
    }


    class RepoViewHolder(itemView: View, listener: ListItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var listenerItem: ListItemClickListener = listener
        var mRepoLink: TextView? = itemView.repolistHyperlink

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listenerItem.onListItemClicked(p0,adapterPosition)
        }


    }

}