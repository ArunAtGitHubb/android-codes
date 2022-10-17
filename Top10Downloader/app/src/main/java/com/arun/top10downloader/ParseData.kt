package com.arun.top10downloader

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.lang.Exception

class ParseData {
    private val TAG = "parse"

    private var feedList = ArrayList<Feed>()

    fun parseXML(xmlData: String): ArrayList<Feed>{
        Log.d(TAG, "parseXML called")
        var text: String? = ""
        var isMain = false

        try{
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(xmlData.reader())
            var eventType = parser.eventType
            var feed = Feed()

            while(eventType != XmlPullParser.END_DOCUMENT){
                val tagName = parser.name?.lowercase()
                when(eventType) {
                    XmlPullParser.START_TAG -> {
                        if(tagName == "entry"){
                            isMain = true
                        }
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> {
                        if(isMain){
                            when(tagName){
                                "entry" -> {
                                    Log.e(TAG, tagName)
                                    feedList.add(feed)
                                    isMain = false
                                    feed = Feed()
                                }
                                "name" -> feed.name = text ?: "Unknown Name"
                                "artist" -> feed.artist = text ?: "Unknown Artist"
                                "summary" -> feed.summary = text ?: "Unknown Summary"
                                "releasedate" -> feed.releaseDate = text ?: "Unknown Release Date"
                                "image" -> feed.imageURL = text ?: "Unknown Image URL"
                            }
                        }
                    }
                }
                eventType = parser.next()
            }
        }catch (e: Exception){
            e.printStackTrace()
            Log.e(TAG, e.message.toString())
        }
        return feedList
    }

}