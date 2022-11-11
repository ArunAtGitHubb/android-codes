package com.jellysoft.deliveryapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jellysoft.deliveryapp.R.layout
import com.jellysoft.deliveryapp.adapters.OrderItemAdapter
import com.jellysoft.deliveryapp.databinding.ActivityOrderDetailBinding

class OrderDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderDetailBinding
    var bottomSheetDialog: BottomSheetDialog? = null
    lateinit var item: OrderItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout.activity_order_detail)
        bottomSheetDialog = BottomSheetDialog(this)
        val orderId = intent.getStringExtra("orderId")
        val username = intent.getStringExtra("username")
        val from = intent.getStringExtra("from")
        Log.d("order", orderId + username + from)


        binding.cusName.text = "John Wick"
        binding.cusaddress1.text = "128, Church, Spic"
        binding.cusaddress2.text = "Tuticorin"
        binding.cusMobile.text = "+91 8945761290"

        binding.resName.text = "Tuty Special"
        binding.resaddress1.text = "67, Bryant nagar"
        binding.resaddress2.text = "Tuticorin"
        binding.resMobile.text = "+91 8945761290"

        binding.tvCustomerName.text = "John Wick"
        binding.tvOrderid.text = orderId
        binding.tvprice.text = "Rs. 250/-"

//        binding.productName.setText(itemDetail.productName)
//        binding.produxtWeight.setText(df.format(itemDetail.priceUnit.toFloat()) + " " + itemDetail.priceUnitName)
//        binding.tvproductprice.setText("Rs. " + df.format(itemDetail.price.toFloat()))
//        binding.produxtQuentity.setText(context.getString(string.quentity) + " : " + itemDetail.quantity)


        val orders = ArrayList<ItemOrder>()
        var order = ItemOrder()

        for (i in 0..4) {
            order = ItemOrder()
            order.productName = "Chicken 65"
            order.priceUnit = "100"
            order.priceUnitName = "g"
            order.price = "200"
            order.quantity = 10
            orders.add(order)
        }

        item = OrderItemAdapter(orders)
        binding.rvorderitems.adapter = item

    }

    fun onclickBack(view: View?) {
        onBackPressed()
    }
}