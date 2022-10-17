package com.arun.contentprovider

import android.Manifest.permission.READ_CONTACTS
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.arun.contentprovider.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private var hasAccessContacts = false

    private var recyclerViewAdapter = Adapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() starts")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        recycleListView.layoutManager = LinearLayoutManager(this)
        recycleListView.adapter = recyclerViewAdapter

        hasAccessContacts = checkPermission(READ_CONTACTS)

        if (!hasAccessContacts) {
            ActivityCompat.requestPermissions(this, arrayOf(READ_CONTACTS), 1)
        }

        binding.fab.setOnClickListener { view ->
            if (checkPermission(READ_CONTACTS)) {
                populateListView().also {
                    Toast.makeText(this, "Contacts Loaded", Toast.LENGTH_LONG).show()
                }
            } else {
                Snackbar.make(
                    view,
                    "Access to the Contacts",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Grant Access") {
                    if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(READ_CONTACTS),
                            Constants.ACCESS_CONTACTS
                        )
                    } else {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", this.packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                }.show()
            }
        }

        Log.d(TAG, "onCreate() ends")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionResult()")
        when (requestCode) {
            Constants.ACCESS_CONTACTS -> {
                hasAccessContacts =
                    grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                Log.d(TAG, "Permission granted: $hasAccessContacts")
            }
        }
    }

    private fun populateListView() {
        val projection = arrayOf(
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.PHONETIC_NAME
        )
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        )
        val contacts = ArrayList<Data>()
        cursor?.use {
            @SuppressLint("Range")
            while (it.moveToNext()) {
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                val phoneticName =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.PHONETIC_NAME))
                        ?: "No Phonetic name"
                val data = Data(name, phoneticName)
                contacts.add(data)
            }
        }
        recyclerViewAdapter.loadNewData(contacts)
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}