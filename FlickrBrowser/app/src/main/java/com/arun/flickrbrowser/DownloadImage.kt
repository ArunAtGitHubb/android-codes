package com.arun.flickrbrowser

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.util.*

class DownloadImage {
    fun downloadImage(name: String): Target {
        val dir = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS)
        val imageFile = File(dir, "${name}_${Date().time}.jpg")
        return object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                Thread {
                    FileOutputStream(imageFile).use {
                        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, it)
                    }
                }.start()
            }
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        }
    }
}