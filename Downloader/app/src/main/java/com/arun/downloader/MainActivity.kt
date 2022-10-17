package com.arun.downloader

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.downloader.PRDownloader
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    DownloadAPIData.OnAPIDownload,
    ParseData.OnDataAvailable,
    DownloadData.OnDownload {
    private var downloadAPIData = DownloadAPIData(this)
    private var hasAccess = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PRDownloader.initialize(applicationContext)

        hasAccess = checkPermission(WRITE_EXTERNAL_STORAGE)
        if (!hasAccess) {
            ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), 1)
        }

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                when (intent.type) {
                    "text/plain" -> {
                        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                            val ex = it.replaceAfter('?', "")
                            Log.d("Main", "new URL $ex")
                            val exactURL = it.dropLast(23)
                            Log.d("Main", "old URL $exactURL")
                            val apiURL = "${exactURL}?__a=1"
                            if (checkPermission(WRITE_EXTERNAL_STORAGE)) {
                                downloadAPIData.execute(apiURL)
                            } else {
                                Snackbar.make(
                                    downloadBtn,
                                    "Allow Access to storage",
                                    Snackbar.LENGTH_INDEFINITE
                                ).setAction("Grant Access") {}.show()
                            }
                        }
                    }
                }
            }
            else -> {
                progressBar.visibility = View.GONE
            }
        }

        downloadBtn.setOnClickListener() {
            if (checkPermission(WRITE_EXTERNAL_STORAGE)) {
                val keyBoardView =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyBoardView.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

                val link = instaLink.text.toString()
                if (link.isEmpty()) {
                    titleColor = getColor(R.color.red)
                    Snackbar.make(it, "Link cannot be empty", Snackbar.LENGTH_LONG)
                        .setAction("Ok") {}.show()
                } else {
                    instaLink.setText("")
                    progressBar.visibility = View.VISIBLE
                    val exactURL = link.dropLast(21)
                    val apiURL = "${exactURL}?__a=1"
                    downloadAPIData = DownloadAPIData(this)
                    downloadAPIData.execute(apiURL)
                }
            } else {
                Snackbar.make(it, "Allow to storage access", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Grant Access") {
                        gotoAppSettings()
                    }.show()
            }
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                gotoAppSettings()
            }
        }
    }

    private fun gotoAppSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", this.packageName, null)
        startActivity(intent)
    }

    override fun onAPIDownloaded(data: String) {
        val parseData = ParseData(arrayListOf(), this)
        parseData.execute(data)
    }

    override fun onDataAvailable(result: ArrayList<String>?, isPhoto: Boolean) {
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val fileExtension = if (isPhoto) "jpg" else "mp4"
        val downloadData = DownloadData(dir, fileExtension, this)
        downloadData.execute(result)
    }

    override fun onDownload(path: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Saved at $path 👍", Toast.LENGTH_LONG).show()
    }

    override fun onParsingError(msg: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        changeStatusBarColor(R.color.red)
    }

    private fun changeStatusBarColor(colorId: Int) {
        window.statusBarColor = getColor(colorId)
        Handler().postDelayed({
            window.statusBarColor = getColor(R.color.purple_700)
        }, 100)
        Handler().postDelayed({
            window.statusBarColor = getColor(colorId)
        }, 200)
        Handler().postDelayed({
            window.statusBarColor = getColor(R.color.purple_700)
        }, 300)
        Handler().postDelayed({
            window.statusBarColor = getColor(colorId)
        }, 400)
        Handler().postDelayed({
            window.statusBarColor = getColor(R.color.purple_700)
        }, 500)
        Handler().postDelayed({
            window.statusBarColor = getColor(colorId)
        }, 600)
        Handler().postDelayed({
            window.statusBarColor = getColor(R.color.purple_700)
        }, 700)
        Handler().postDelayed({
            window.statusBarColor = getColor(colorId)
        }, 800)
        Handler().postDelayed({
            window.statusBarColor = getColor(R.color.purple_700)
        }, 900)
        Handler().postDelayed({
            window.statusBarColor = getColor(colorId)
        }, 1000)
        Handler().postDelayed({
            window.statusBarColor = getColor(R.color.purple_700)
        }, 1100)
    }
}