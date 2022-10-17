package com.arun.downloader

import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ParseData(
    private var urlList: ArrayList<String>,
    private val listener: OnDataAvailable
) : AsyncTask<String, Void, ArrayList<String>>() {

    private var isPhoto = true

    interface OnDataAvailable {
        fun onDataAvailable(result: ArrayList<String>?, isPhoto: Boolean)
        fun onParsingError(msg: String)
    }

    override fun doInBackground(vararg json: String): ArrayList<String>? {
        var parent = JSONObject()
        var data: JSONArray
        try {
            parent = JSONObject(json[0])
                .getJSONObject("graphql")
                .getJSONObject("shortcode_media")

            data = parent.getJSONObject("edge_sidecar_to_children").getJSONArray("edges")
            when (parent.getBoolean("is_video")) {
                true -> {
                    isPhoto = false
                    videoPost(parent.getString("video_url"))
                }
                false -> {
                    isPhoto = true
                    noVideoPost(data, true)
                }
            }
        } catch (e: JSONException) {
            try {
                data = parent.getJSONArray("display_resources")
                when (parent.getBoolean("is_video")) {
                    true -> {
                        isPhoto = false
                        videoPost(parent.getString("video_url"))
                    }
                    false -> {
                        isPhoto = true
                        noVideoPost(data, false)
                    }
                }
            } catch (e: JSONException) {
                return null
            }
        }
        return urlList
    }

    override fun onPostExecute(result: ArrayList<String>?) {
        super.onPostExecute(result)
        if (result != null) {
            listener.onDataAvailable(result, isPhoto)
        } else {
            listener.onParsingError("Enter Valid URL")
        }
        cancel(true)
    }

    private fun videoPost(url: String) {
        urlList.add(url)
    }

    private fun noVideoPost(data: JSONArray?, grpPost: Boolean) {
        var src: String
        var obj: JSONArray
        for (i in 0 until data!!.length()) {
            if (grpPost) {
                obj = data.getJSONObject(i).getJSONObject("node").getJSONArray("display_resources")
                src = obj.getJSONObject(2).getString("src")
                urlList.add(src)
            } else {
                src = data.getJSONObject(2).getString("src")
                urlList.add(src)
                break
            }
        }
    }
}