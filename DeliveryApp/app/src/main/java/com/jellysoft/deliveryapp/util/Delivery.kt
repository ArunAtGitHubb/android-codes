package com.jellysoft.deliveryapp.util

import android.provider.BaseColumns
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_CITY_ID
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_FOOD_ID
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_FOOD_NAME
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_FOOD_PRICE
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_FOOD_QTY
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_RES_ID
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_RES_NAME
import com.jellysoft.deliveryapp.util.DeliveryReaderContract.DeliveryEntry.COLUMN_NAME_USER_ID

object DeliveryReaderContract {
    object DeliveryEntry : BaseColumns {
        const val TABLE_DELIVERY = "delivery"
        const val COLUMN_NAME_RES_ID = "res_id"
        const val COLUMN_NAME_RES_NAME = "res_name"
        const val COLUMN_NAME_FOOD_QTY = "food_qty"
        const val COLUMN_NAME_CITY_ID = "city_id"
        const val COLUMN_NAME_FOOD_ID = "food_id"
        const val COLUMN_NAME_FOOD_NAME = "food_name"
        const val COLUMN_NAME_FOOD_TYPE = "food_id"
        const val COLUMN_NAME_CAT_ID = "cat_id"
        const val COLUMN_NAME_FOOD_PRICE = "food_price"
        const val COLUMN_NAME_USER_ID = "user_id"
    }

    const val SQL_CREATE_TABLE_DELIVERY =
        "CREATE TABLE ${DeliveryEntry.TABLE_DELIVERY} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME_USER_ID TEXT, " +
                "$COLUMN_NAME_FOOD_ID TEXT, " +
                "$COLUMN_NAME_FOOD_NAME TEXT, " +
                "$COLUMN_NAME_FOOD_PRICE INT, " +
                "$COLUMN_NAME_RES_ID TEXT, " +
                "$COLUMN_NAME_RES_NAME TEXT, " +
                "$COLUMN_NAME_CITY_ID TEXT, " +
                "$COLUMN_NAME_FOOD_QTY INT DEFAULT 0) "

    const val SQL_DELETE_TABLE_DELIVERY = "DROP TABLE IF EXISTS ${DeliveryEntry.TABLE_DELIVERY}"
}