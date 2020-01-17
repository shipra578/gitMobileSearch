package com.shipra.android.gitmobilesearch.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.model.ItemsPojo
import com.shipra.android.gitmobilesearch.ui.ListItemClickListener
import com.shipra.android.gitmobilesearch.ui.MainActivity
import com.shipra.android.gitmobilesearch.ui.adapter.ItemListAdapter.ItemViewHolder
import kotlinx.android.synthetic.main.home_list_adapter_layout.view.*

class ItemListAdapter(private val productList: ArrayList<ItemsPojo>?, private val mContext: Context) : RecyclerView.Adapter<ItemViewHolder>()/*, Filterable*/ {

    var mListener: ListItemClickListener = mContext as MainActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_list_adapter_layout, parent, false)

        return ItemViewHolder(v,mListener)
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
        holder.mWatcherCount!!.text = "watchers:  ${watcher_count}"
        holder.mCommitCount!!.text = "0"
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    /* fun setAll(aproductList: MutableList<ItemsPojo>) {

         if (aproductList != null) {
             productList!!.addAll(aproductList as Collection<ItemsPojo>)
         }
         notifyDataSetChanged()
     }*/

    class ItemViewHolder(itemView: View,listener: ListItemClickListener ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

         var listenerItem: ListItemClickListener  = listener
        var mItemImage: ImageView? = itemView.imageView
        var mRepoName: TextView? = itemView.name
        var mRepoFullName: TextView? = itemView.fullname
        var mWatcherCount: TextView? = itemView.watcher_count
        var mCommitCount: TextView? = itemView.commit_count

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listenerItem.onListItemClicked(p0,adapterPosition)
        }


    }

    /*   override fun getFilter(): Filter {

           return exampleFilter
       }

       private var exampleFilter: Filter = object : Filter() {

           override fun performFiltering(charSequence: CharSequence): FilterResults {
               var filteredList: ArrayList<ItemsPojo> = arrayListOf()
               var results = FilterResults()
               productListCopy = productList
               if (charSequence == null || charSequence.length == 0) {
                   productListCopy = productList
                   results.values = productListCopy
               } else {
                   var filterString = charSequence.toString()
                   if (productListCopy != null) {
                       for (item in productListCopy!!){

                           if(item.name.contains(filterString)){
                               filteredList.add(item)
                           }
                       }
                   }
                   results.values = filteredList
               }
               return results
           }

           override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {

               filterResults.values.toString()
               productListCopy?.clear()
               productListCopy?.addAll((filterResults.values as ArrayList<ItemsPojo>?)!!)
               notifyDataSetChanged()
           }
       }*/


}