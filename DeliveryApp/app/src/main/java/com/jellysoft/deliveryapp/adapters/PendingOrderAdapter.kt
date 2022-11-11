package com.jellysoft.deliveryapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jellysoft.deliveryapp.ItemOrder
import com.jellysoft.deliveryapp.OrderDetailActivity
import com.jellysoft.deliveryapp.R.layout
import com.jellysoft.deliveryapp.adapters.PendingOrderAdapter.OrderViewHolder
import com.jellysoft.deliveryapp.databinding.ItemOrderlistBinding

class PendingOrderAdapter(data: ArrayList<ItemOrder>, username: String) :
    Adapter<OrderViewHolder>() {
    private val data: List<ItemOrder>
    private val username: String
    private var context: Context? = null

    init {
        this.data = data
        this.username = username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(layout.item_orderlist, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.setModel(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OrderViewHolder(itemView: View) : ViewHolder(itemView) {
        var binding: ItemOrderlistBinding

        init {
            binding = ItemOrderlistBinding.bind(itemView)
        }

        @SuppressLint("SetTextI18n")
        fun setModel(datum: ItemOrder?) {
            if (datum!!.date != null) {
                binding.tvtime.text = datum.date
            }
            if (datum != null) {
                binding.tvCustomername.text = datum.firstname
                binding.tvaddress1.text = datum.area + ", " + datum.city
                binding.tvaddress2.text = datum.area + " " + datum.landmark + " " + datum.pincode
                binding.orderid.text = "Order ID: " + datum.orderId
            }
            itemView.setOnClickListener { v: View? ->
                val intent = Intent(context, OrderDetailActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("orderId", datum.orderId)
                intent.putExtra("from", "pending")
                context!!.startActivity(intent)
            }
        }
    }
}