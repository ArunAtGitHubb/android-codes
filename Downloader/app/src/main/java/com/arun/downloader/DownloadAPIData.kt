package com.arun.downloader

import android.os.AsyncTask
import java.lang.Exception
import java.net.URL

class DownloadAPIData(private val lister: OnAPIDownload) : AsyncTask<String, Void, String>() {

    interface OnAPIDownload {
        fun onAPIDownloaded(data: String)
    }

    override fun doInBackground(vararg urls: String?): String {
        var result = ""
        try{
            result = URL(urls[0]).readText()
        }catch (e: Exception){
            return ""
        }
        return result
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        lister.onAPIDownloaded(result)
        cancel(true)
    }

    // graphql > shortcode_media > edge_sidecar_to_children > edges[] > > node > display_resources[]
}