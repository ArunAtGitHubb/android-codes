package com.jellysoft.deliveryapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jellysoft.deliveryapp.databinding.ActivityLoginBinding
import com.jellysoft.deliveryapp.db.DeliveryDbHelper
import com.jellysoft.deliveryapp.util.API
import com.jellysoft.deliveryapp.util.Constant
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.SQL_CREATE_TABLE_DELIVERY
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.SQL_DELETE_TABLE_DELIVERY
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var dbHelper: DeliveryDbHelper? = null
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.tvvegi.transitionName = intent.getStringExtra("position")

        dbHelper = DeliveryDbHelper(applicationContext)
        db = dbHelper!!.writableDatabase
        db.execSQL(SQL_DELETE_TABLE_DELIVERY)
        db.execSQL(SQL_CREATE_TABLE_DELIVERY)

        binding.loginBtn.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            login(username, password)
        }
    }

    private fun login(username: String, password: String){
        val client = AsyncHttpClient()
        val params = RequestParams()
        val jsObj: JsonObject = Gson().toJsonTree(API()) as JsonObject
        jsObj.addProperty("method_name", "login")
        jsObj.addProperty("username", username)
        jsObj.addProperty("password", password)
        params.put("data", API.toBase64(jsObj.toString()))
        client.post(Constant.API_URL, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)

                try {
                    val mainJson = JSONObject(result)
                    val msgJson = mainJson.getJSONArray(Constant.ARRAY_NAME).getJSONObject(0)
                    Toast.makeText(applicationContext, msgJson.getString("msg"), Toast.LENGTH_SHORT).show()
                    if (msgJson.getString("success") == "1"){
                        finish()
                        startActivity(Intent(applicationContext, MainActivity::class.java).putExtra("username", username))
                    }
                } catch (e: Exception){
                    Log.d("err", e.toString())
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
            }
        })
    }
}