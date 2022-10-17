package com.arun.top10downloader

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private var feedURL: String =
        "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
    private var feedLimit = 10
    private lateinit var connectionManager: ConnectivityManager
    private var networkInfo: NetworkInfo? = null
    private var downloadData: DownloadData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate() called")
        if (savedInstanceState != null) {
            feedURL = savedInstanceState.getString("feedURL").toString()
            feedLimit = savedInstanceState.getInt("feedLimit")
        }
        download(feedURL.format(feedLimit))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var newURL: String
        var titleText = "Free Apps"
        when (item.itemId) {
            R.id.menuFreeApps -> {
                newURL =
                    "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
                titleText = "Free Apps"
                title = "Top $feedLimit $titleText"
            }
            R.id.menuPaidApps -> {
                newURL =
                    "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=%d/xml"
                titleText = "Paid Apps"
                title = "Top $feedLimit $titleText"
            }
            R.id.menuSongs -> {
                newURL =
                    "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml"
                titleText = "Songs"
                title = "Top $feedLimit $titleText"
            }
            R.id.menu10, R.id.menu25 -> {
                return if (!item.isChecked) {
                    item.isChecked = true
                    feedLimit = 35 - feedLimit
                    title = "Top $feedLimit $titleText"
                    download(feedURL.format(feedLimit))
                    super.onOptionsItemSelected(item)
                }else{
                    super.onOptionsItemSelected(item)
                }
            }
            R.id.menuRefresh -> {
                download(feedURL.format(feedLimit))
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        if (newURL != feedURL) {
            feedURL = newURL
            download(feedURL.format(feedLimit))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "url: $feedURL, limit: $feedLimit")
        outState.putString("feedURL", feedURL)
        outState.putInt("feedLimit", feedLimit)
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadData?.cancel(true)
    }

    private fun download(url: String) {
        Log.d(TAG, "the url $url")
        connectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkInfo = connectionManager.activeNetworkInfo
        if (networkInfo?.isConnected == true) {
            downloadData = DownloadData(progressBar, this, listView)
            downloadData?.execute(url)
        } else {
            downloadData?.cancel(true)
            Toast.makeText(this, "Turn on your mobile network", Toast.LENGTH_LONG).show()
        }
    }
}

