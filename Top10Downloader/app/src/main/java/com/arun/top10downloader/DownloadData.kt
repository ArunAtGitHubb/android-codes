package com.arun.top10downloader

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import java.io.FileNotFoundException
import java.net.URL
import kotlin.properties.Delegates


class DownloadData(
    progressBar: ProgressBar?,
    context: Context,
    listView: ListView
) : AsyncTask<String, Void, String>() {
    private var progressBar: ProgressBar by Delegates.notNull()
    private var context: Context by Delegates.notNull()
    var listView: ListView by Delegates.notNull()

    init {
        this.context = context
        if (progressBar != null) {
            this.progressBar = progressBar
        }
        this.listView = listView
    }

    private val TAG = "DownloadData"
    override fun doInBackground(vararg urls: String?): String {
        val rssFeed = apiRequest(urls[0])
        if (rssFeed.isEmpty()) {
            Log.e(TAG, "doInBackground(): Error on downloading")
        }
        return rssFeed
    }

    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.visibility = View.VISIBLE
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        val parser = ParseData()
        val data = parser.parseXML(result)
        val feedAdapter = FeedAdapter(context, R.layout.list_item_record, data) // (where: context, how: layout, what: data)
        listView.adapter = feedAdapter
        progressBar.visibility = View.GONE
        cancel(true)
    }

    private fun apiRequest(urlPath: String?): String {
        val result = StringBuilder()
        try {
            val connection = URL(urlPath).openConnection()
            connection.inputStream.buffered().reader().use { result.append(it.readText()) }
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is FileNotFoundException -> Log.e(TAG, e.message.toString())
                else -> Log.e(TAG, e.message.toString())
            }
        }
        return result.toString()
//        return URL(urlPath).readText() // shorter version. For valid URL path
    }
}
