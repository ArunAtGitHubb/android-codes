package com.arun.contentprovider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val contactName: TextView = view.findViewById(R.id.contact_name)
    val phoneticName : TextView = view.findViewById(R.id.phoneticName)
}

class Adapter(private var dataList: List<Data>): RecyclerView.Adapter<DataViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_details, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = dataList[position]
        holder.contactName.text = data.name
        holder.phoneticName.text = data.phoneticName
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun loadNewData(newList: List<Data>){
        dataList = newList
        notifyDataSetChanged()
    }
}