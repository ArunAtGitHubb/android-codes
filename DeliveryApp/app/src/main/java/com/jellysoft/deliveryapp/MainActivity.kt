package com.jellysoft.deliveryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jellysoft.deliveryapp.R.id
import com.jellysoft.deliveryapp.R.layout
import com.jellysoft.deliveryapp.databinding.ActivityMainBinding
import com.jellysoft.deliveryapp.fragments.PendingFragment

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = intent.getStringExtra("username")
        binding = DataBindingUtil.setContentView(this, layout.activity_main)

        loadFragment(PendingFragment(username))
        binding!!.tabPending.setOnClickListener { loadFragment(PendingFragment(username)) }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(id.frame, fragment).commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("TAG", "onRequestPermissionsResult: ")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}