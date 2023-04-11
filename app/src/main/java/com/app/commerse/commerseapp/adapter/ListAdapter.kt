package com.app.commerse.commerseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.commerse.commerseapp.R
import com.app.commerse.commerseapp.models.WatchModel

class ListAdapter :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var mWatches: List<WatchModel>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWatchTitle = itemView.findViewById<TextView>(R.id.titleWatch)
        val tvSubTitle = itemView.findViewById<TextView>(R.id.subTitle)
        val tvWatchPrice = itemView.findViewById<TextView>(R.id.watchPrice)
    }

    fun setWatches(mWatchess: List<WatchModel>) {
        mWatches = mWatchess
        notifyDataSetChanged()
    }
    fun getWatches(): List<WatchModel>? {
      return mWatches?: null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_watch, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ListAdapter.ViewHolder, position: Int) {
        val watch: WatchModel = mWatches?.get(position) ?: return
        viewHolder.tvWatchTitle.setText(watch.name)
        viewHolder.tvSubTitle.setText(watch.series)
        viewHolder.tvWatchPrice.setText("$" + watch.price)

    }

    override fun getItemCount(): Int {
        return mWatches?.size ?: return 0
    }
}