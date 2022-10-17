package com.arun.flickrbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arun.flickrbrowser.databinding.ActivityDataBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.content_data.*


class DataActivity : AppCompatActivity() {
    private val TAG = "dataActivity"
    private lateinit var binding: ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_data)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = intent.getSerializableExtra("data") as Data

        data_author.text = data.author
        Picasso.get()
            .load(data.link)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(data_image)
        data_title.text = data.title
        data_tags.text = data.tags

        downloadBtn.setOnClickListener {
            val downloadImage = DownloadImage()
            Picasso.get()
                .load(data.link)
                .into(downloadImage.downloadImage(data.title))
            Snackbar.make(it, "Image Downloaded", Snackbar.LENGTH_LONG).setAction("Ok"){}.show()
        }
    }

}