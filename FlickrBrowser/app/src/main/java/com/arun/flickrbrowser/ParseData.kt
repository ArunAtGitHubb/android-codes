package com.arun.flickrbrowser

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject

class ParseData(private val listener: OnDataAvailable) :
    AsyncTask<String, Void, ArrayList<Data>>() {
    private val TAG = "ParseData"

    interface OnDataAvailable {
        fun onDataAvailable(data: List<Data>)
        fun onError(e: Exception)
    }

    override fun doInBackground(vararg data: String?): ArrayList<Data>? {
        val dataList = ArrayList<Data>()
        try {
            val jsonData = JSONObject(data[0]!!)
            val items = jsonData.getJSONArray("items")
            var element: Data
            var index = 0
            while (index < items.length()) {
                val item = items.getJSONObject(index);
                val imageURL = JSONObject(item.getString("media")).getString("m")
                val tagList = item.getString("tags").split(" ")
                val tags = StringBuilder()
                for (tag in tagList) {
                    tags.append("#$tag ")
                }
                element = Data(
                    item.getString("title"),
                    item.getString("author"),
                    item.getString("author_id"),
                    imageURL.replaceFirst("_m.jpg", "_b.jpg"),
                    tags.toString(),
                    imageURL
                )
                dataList.add(element)
                index++
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            listener.onError(e)
        }
        return if (dataList.isNotEmpty()) {
            dataList
        }else {
            null
        }
    }

    override fun onPostExecute(result: ArrayList<Data>?) {
        super.onPostExecute(result)
        if (result != null) {
            listener.onDataAvailable(result)
        }
        cancel(true)
    }
}