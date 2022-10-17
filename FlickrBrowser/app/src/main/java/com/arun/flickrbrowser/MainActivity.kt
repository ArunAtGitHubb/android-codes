package com.arun.flickrbrowser

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
    DownloadData.OnDownloadComplete,
    ParseData.OnDataAvailable,
    RecyclerClickListener.OnRecyclerClickListener {
    private val TAG = "MainActivity"
    private val recyclerViewAdapter = RecyclerViewAdapter(ArrayList())
    private var downloadData: DownloadData? = null
    private val baseURL = "https://www.flickr.com/services/feeds/photos_public.gne"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tags = PreferenceManager.getDefaultSharedPreferences(applicationContext).getString("query", "gtav").toString()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.addOnItemTouchListener(RecyclerClickListener(this, recycleView, this))
        recycleView.adapter = recyclerViewAdapter

        val url = createURL(baseURL,tags)
        downloadData = DownloadData(this)
        downloadData!!.execute(url)

        when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK){
            Configuration.UI_MODE_NIGHT_YES -> {
                recycleView.setBackgroundColor(resources.getColor(R.color.recycler_dark_color))
                fab.setBackgroundColor(resources.getColor(R.color.fab_dark_color))
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                recycleView.setBackgroundColor(resources.getColor(R.color.recycler_light_color))
                fab.setBackgroundColor(resources.getColor(R.color.fab_light_color))
            }
        }

        fab.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val networkInfo: NetworkInfo?
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected
            if (networkInfo?.isConnected == true) {
                downloadData = DownloadData(this)
                downloadData!!.execute(url)
            } else {
                Snackbar.make(
                    it,
                    "Make sure, device is connected to internet then click the reload button",
                    Snackbar.LENGTH_LONG
                ).setAction("OK") {}.show()
            }
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, DataActivity::class.java)
        if(recyclerViewAdapter.getItem(position) == null){
            Snackbar.make(
                view,
                "Make sure, device is connected to internet then click the reload button",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("OK") {}.show()
        }
        intent.putExtra("data", recyclerViewAdapter.getItem(position))
        startActivity(intent)
    }

    override fun onItemLongClick(view: View, position: Int) {
        Snackbar.make(
            view,
            "$position item long clicked",
            Snackbar.LENGTH_LONG
        ).setAction("OK", null).show()
    }

    private fun createURL(baseURL: String, tags: String): String {
        return Uri.parse(baseURL)
            .buildUpon()
            .appendQueryParameter("tags", tags)
            .appendQueryParameter("lang", "en-us")
            .appendQueryParameter("tagmode", "Any")
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1").toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_search ->{
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String) {
        Log.d(TAG, ".onDownloadComplete()")
        val parseData = ParseData(this)
        parseData.execute(data)
        progressBar.visibility = View.GONE
        downloadData?.cancel(true)
    }

    override fun onDataAvailable(data: List<Data>) {
        if (data.isEmpty()) {
            Toast.makeText(this, "No Results Found", Toast.LENGTH_LONG).show()
        }
        recyclerViewAdapter.loadNewData(data)
        Toast.makeText(this, "Data loaded from server successfully 👍", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onDataAvailable() data -> $data")
    }

    override fun onError(e: Exception) {
        Log.d(TAG, e.message.toString())
    }

    override fun onResume() {
        Log.d(TAG, "onResume starts")
        val sharePref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val tag = sharePref.getString("query", "").toString()
        Log.d(TAG, tag)
        if(tag.isNotEmpty()){
            Log.d(TAG, "inside tag.isNotEmpty() $tag")
            val url = createURL(baseURL, tag)
            progressBar.visibility = View.VISIBLE
            downloadData = DownloadData(this)
            downloadData!!.execute(url)
        }
        super.onResume()
    }
}