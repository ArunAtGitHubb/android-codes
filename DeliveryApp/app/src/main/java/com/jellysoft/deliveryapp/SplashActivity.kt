package com.jellysoft.deliveryapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jellysoft.deliveryapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 1000)
    }
}
