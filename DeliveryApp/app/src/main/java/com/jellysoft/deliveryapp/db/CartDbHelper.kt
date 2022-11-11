package com.jellysoft.deliveryapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jellysoft.deliveryapp.util.DeliveryReaderContract

class DeliveryDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DeliveryReaderContract.SQL_CREATE_TABLE_DELIVERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DeliveryReaderContract.SQL_DELETE_TABLE_DELIVERY)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DeliveryReader.db"
    }
}