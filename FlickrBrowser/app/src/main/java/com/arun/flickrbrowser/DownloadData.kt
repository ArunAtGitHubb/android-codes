package com.arun.flickrbrowser

import android.os.AsyncTask
import android.util.Log
import java.net.URL

class DownloadData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {
    private val TAG = "DownloadData"

    interface OnDownloadComplete {
        fun onDownloadComplete(data: String)
    }

    override fun doInBackground(vararg urls: String?): String {
        return try {
            URL(urls[0]).readText()
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            e.toString()
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d(TAG, ".onPostExecute()")
        if (result != null) {
            listener.onDownloadComplete(result.toString())
        }
        cancel(true)
    }
}