package com.arun.downloader

import android.os.AsyncTask
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import java.io.File
import java.util.*

class DownloadData(
    private val dir: File,
    private val fileExtension: String,
    private val listener: OnDownload
) :
    AsyncTask<ArrayList<String>, Void, String>() {

    interface OnDownload {
        fun onDownload(path: String)
    }

    override fun doInBackground(vararg urls: ArrayList<String>): String {
        var fileName = ""
        for (url in urls[0]) {
            fileName = "insta_${Date().time}.$fileExtension"
            PRDownloader.download(url, dir.toString(), fileName)
                .build()
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {}
                    override fun onError(error: Error?) {}
                })
        }
        return dir.toString()
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        listener.onDownload(result)
        cancel(true)
    }
}