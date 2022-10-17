package com.arun.top10downloader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ViewHolder(view: View){
    val itemName: TextView = view.findViewById(R.id.itemName)
    val itemArtist: TextView = view.findViewById(R.id.itemArtist)
    val itemSummary: TextView = view.findViewById(R.id.itemSummary)
}

class FeedAdapter(
    context: Context,
    private val resource: Int,
    private val feedList: List<Feed>
) : ArrayAdapter<Feed>(context, resource) {
    val tag = "feedAdapter"
    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return feedList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        val viewHolder: ViewHolder
        if(convertView == null){
            view = inflater.inflate(resource, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentItem = feedList[position]
        viewHolder.itemName.text = currentItem.name
        viewHolder.itemArtist.text = currentItem.artist
        viewHolder.itemSummary.text = currentItem.summary
        return view
    }
}