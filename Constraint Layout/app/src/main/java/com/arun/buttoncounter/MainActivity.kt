package com.arun.buttoncounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val button = R.id.button
    val okBtn = R.id.okBtn
    val cancelBtn = R.id.cancelBtn
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onBtnClick(v: View){
        setContentView(R.layout.note_layout)
    }

    fun onCancelBtnClick(v: View){
        setContentView(R.layout.activity_main)
    }
}
