package com.jellysoft.deliveryapp.util

import android.util.Base64
import com.google.gson.annotations.SerializedName
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class API {
    @SerializedName("sign")
    private val sign: String?

    @SerializedName("salt")
    private val salt: String

    init {
        val apiKey = "viaviweb"
        salt = "" + randomSalt
        sign = md5(apiKey + salt)
    }

    private val randomSalt: Int
        private get() {
            val random = Random()
            return random.nextInt(900)
        }

    private fun md5(input: String): String? {
        return try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(input.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (i in messageDigest.indices) hexString.append(
                String.format(
                    "%02x",
                    messageDigest[i]
                )
            )
            hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        fun toBase64(input: String): String {
            val encodeValue = Base64.encode(input.toByteArray(), Base64.DEFAULT)
            return String(encodeValue)
        }
    }
}