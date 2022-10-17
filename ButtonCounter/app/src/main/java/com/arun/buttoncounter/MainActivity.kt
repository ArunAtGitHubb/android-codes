package com.arun.buttoncounter

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import java.net.URI
import java.util.*

private const val TAG = "MainActivity"
private const val TEXT_CONTENTS = "TextContents"
private const val VIDEO_CONTENTS = "VideoContents"
private var usersMap = mutableMapOf<String, Int>()

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    private var clearBtn: Button? = null
    private var editText: EditText? = null
    private var textView: TextView? = null
    private var image: ImageView? = null
    private var video: VideoView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()" + Date().time)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.okBtn)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)
        clearBtn = findViewById(R.id.clearBtn)
        image = findViewById(R.id.imageView)
        video = findViewById(R.id.videoView)

        textView?.movementMethod = ScrollingMovementMethod()

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                when (intent.type!!) {
                    "text/plain" -> {
                        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                            textView?.text = textView?.text.toString() + it + "\n";
                        }
                    }
                    "image/*" ->{
                        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
                            image?.setImageURI(it)
                        }
                    }
                    "video/*" -> {
                        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
                            Log.d(TAG, it.toString())
                            video?.setVideoURI(it)
                            video?.start()
                        }
                    }
                }
            }
        }

        button?.setOnClickListener {
            val name: String = editText?.text.toString()
            if (name == "") {
                Toast.makeText(this, "Name can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (usersMap[name] == null) {
                    usersMap[name] = 1
                    textView?.append("$name clicked the button 1 time\n")
                } else {
                    usersMap[name] = usersMap[name]!! + 1
                    textView?.append("$name clicked the button ${usersMap[name]} times\n")
                }
            }
        }

        clearBtn?.setOnClickListener {
            textView?.text = ""
            editText!!.text.clear()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(
            TAG,
            "onRestoreInstanceState()" + Date().time + savedInstanceState.getString(TEXT_CONTENTS)
        )
        textView?.text = savedInstanceState.getString(TEXT_CONTENTS)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()" + Date().time)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()" + Date().time)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // make sure this onSaveInstanceState() is over-rode
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState()" + Date().time + textView?.text.toString())
        outState.putString(TEXT_CONTENTS, textView?.text.toString())
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()" + Date().time)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()" + Date().time)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()" + Date().time)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()" + Date().time)
    }
}