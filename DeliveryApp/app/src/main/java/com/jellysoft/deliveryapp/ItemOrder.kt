package com.jellysoft.deliveryapp

import com.google.gson.annotations.SerializedName
import com.jellysoft.deliveryapp.models.PendingOrdersRoot.Orderaddress
import com.jellysoft.deliveryapp.models.PendingOrdersRoot.OrderproductsItem
import com.jellysoft.deliveryapp.models.User

class ItemOrder {
    var date: String? = null
    var addressId = 0
    var createdAt: String? = null
    var orderAddress: Orderaddress? = null
    var paymentType = 0
    var orderProducts: List<OrderproductsItem>? = null
    var shippingCharge: String? = null
    var updatedAt: String? = null
    var userId = 0
    var totalAmount: String? = null
    var subtotal: String? = null
    var startDelivery = 0
    var id = 0
    var deliveryBoyId = 0
    var orderId: String? = null
    var user: User? = null
    var couponDiscount: String? = null
    var status = 0
    
    var priceUnit: String? = null
    var image: String? = null
    var quantity = 0
    var totalPrice: String? = null
    var priceUnitName: String? = null
    var price: String? = null
    var productName: String? = null

    var area: String? = null
    var pincode = 0
    var firstname: String? = null
    var addressType = 0
    var city: String? = null
    var latitude: String? = null
    private var addressLine: String? = null
    var isDefault: Any? = null
    var lastname: String? = null
    var number: String? = null
    var altNumber: String? = null
    var landmark: String? = null
    var longitude: String? = null
}