package com.arun.flickrbrowser

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_entry.view.*

class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val thumbnail: ImageView = view.thumbnails
    val dataTitle: TextView = view.dataTitle
}

class RecyclerViewAdapter(private var dataList: List<Data>) :
    RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_entry, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataItem = dataList[position]

        Picasso.get()
            .load(dataItem.image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.thumbnail)

        holder.dataTitle.text = dataItem.title
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getItem(position: Int): Data? {
        return if(dataList.isNotEmpty()) dataList[position] else null
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadNewData(newData: List<Data>) {
        dataList = newData
        notifyDataSetChanged()
    }
}