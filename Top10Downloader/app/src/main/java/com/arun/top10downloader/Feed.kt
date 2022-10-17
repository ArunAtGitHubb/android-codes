package com.arun.top10downloader

class Feed {
    private val TAG = "Feed"
    var name: String? = null
    var artist: String? = null
    var releaseDate: String? = null
    var imageURL: String? = null
    var summary: String? = null

    override fun toString(): String {
        return "$name $artist $releaseDate $imageURL $summary"
    }
}