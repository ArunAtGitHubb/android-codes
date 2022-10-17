package com.arun.working_with_sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.arun.working_with_sqlite.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val db = baseContext.openOrCreateDatabase("sqlite-test.db", MODE_PRIVATE, null)

        enterBtn.setOnClickListener {
            doInBackground(db)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun doInBackground(db: SQLiteDatabase){
        Thread {
            val values = ContentValues().apply {
                put("name", nameText.text.toString())
                put("mail", mailText.text.toString())
                put("phone", phoneText.text.toString())
            }
            val id = db.insert("contacts", null, values)
            Log.d("main", "id: $id")
            val query = db.rawQuery("SELECT * FROM contacts", null)

            query.use {
                while (it.moveToNext()) {
                    with(query) {
                        val rowId = getLong(0)
                        val name1 = getString(1)
                        val mail1 = getString(2)
                        val phone1 = getString(3)
                        Log.d("main", "id: $rowId, name: $name1, mail: $mail1, phone: $phone1")
                    }
                }
            }
            runOnUiThread {
                Toast.makeText(this, "Inserted with id: $id", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}