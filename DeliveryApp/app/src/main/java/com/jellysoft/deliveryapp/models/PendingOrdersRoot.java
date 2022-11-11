package com.jellysoft.deliveryapp.models;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PendingOrdersRoot {

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<DataItem> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }


    public static class OrderproductsItem {

        @SerializedName("price_unit")
        private String priceUnit;

        @SerializedName("image")
        private String image;

        @SerializedName("quantity")
        private int quantity;

        @SerializedName("total_price")
        private String totalPrice;

        @SerializedName("updated_at")
        private String updatedAt;

        @SerializedName("price_unit_name")
        private String priceUnitName;

        @SerializedName("price")
        private String price;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("id")
        private int id;

        @SerializedName("order_id")
        private int orderId;

        @SerializedName("product_name")
        private String productName;

        public String getPriceUnit() {
            return priceUnit;
        }

        public String getImage() {
            return image;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public String getPriceUnitName() {
            return priceUnitName;
        }

        public String getPrice() {
            return price;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public int getId() {
            return id;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getProductName() {
            return productName;
        }
    }

    public static class Orderaddress {

        @SerializedName("area")
        private String area;

        @SerializedName("pincode")
        private int pincode;

        @SerializedName("fullname")
        private String firstname;

        @SerializedName("address_type")
        private int addressType;

        @SerializedName("city")
        private String city;

        @SerializedName("latitude")
        private String latitude;

        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("address")
        private String addressLine;

        public String getAddressLine() {
            return addressLine;
        }

        public void setAddressLine(String addressLine) {
            this.addressLine = addressLine;
        }

        @SerializedName("is_default")
        private Object isDefault;

        @SerializedName("lastname")
        private String lastname;

        @SerializedName("number")
        private String number;


        @SerializedName("updated_at")
        private String updatedAt;


        @SerializedName("id")
        private int id;

        @SerializedName("alt_number")
        private String altNumber;

        @SerializedName("landmark")
        private String landmark;

        @SerializedName("order_id")
        private int orderId;

        @SerializedName("longitude")
        private String longitude;

        public String getArea() {
            return area;
        }

        public int getPincode() {
            return pincode;
        }

        public String getFirstname() {
            String first = String.valueOf(firstname.charAt(0));
            return firstname.replace(first, first.toUpperCase());
        }

        public int getAddressType() {
            return addressType;
        }

        public String getCity() {
            return city;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getCreatedAt() {
            return createdAt;
        }


        public Object getIsDefault() {
            return isDefault;
        }

        public String getLastname() {
            return lastname;
        }

        public String getNumber() {
            return number;
        }


        public String getUpdatedAt() {
            return updatedAt;
        }


        public int getId() {
            return id;
        }

        public String getAltNumber() {
            return altNumber;
        }

        public String getLandmark() {
            return landmark;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getLongitude() {
            return longitude;
        }
    }

    public static class DataItem {

        @SerializedName("date")
        private String date;

        @SerializedName("address_id")
        private int addressId;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("orderaddress")
        private Orderaddress orderaddress;

        @SerializedName("payment_type")
        private int paymentType;

        @SerializedName("orderproducts")
        private List<OrderproductsItem> orderproducts;

        @SerializedName("shipping_charge")
        private String shippingCharge;

        @SerializedName("updated_at")
        private String updatedAt;

        @SerializedName("user_id")
        private int userId;

        @SerializedName("total_amount")
        private String totalAmount;

        @SerializedName("subtotal")
        private String subtotal;

        @SerializedName("start_delivery")
        private int startDelivery;

        @SerializedName("id")
        private int id;

        @SerializedName("deliveryBoy_id")
        private int deliveryBoyId;

        @SerializedName("order_id")
        private String orderId;

        @SerializedName("user")
        private User user;

        @SerializedName("coupon_discount")
        private String couponDiscount;

        @SerializedName("status")
        private int status;

        public String getDate() {
            if (date != null && !date.isEmpty()) {
                SimpleDateFormat output = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    return output.format(simpleDateFormat.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return date;
        }

        public int getAddressId() {
            return addressId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public Orderaddress getOrderaddress() {
            return orderaddress;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public List<OrderproductsItem> getOrderproducts() {
            return orderproducts;
        }

        public String getShippingCharge() {
            return shippingCharge;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public int getUserId() {
            return userId;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public String getSubtotal() {
            return subtotal;
        }

        public int getStartDelivery() {
            return startDelivery;
        }

        public int getId() {
            return id;
        }

        public int getDeliveryBoyId() {
            return deliveryBoyId;
        }

        public String getOrderId() {
            return orderId;
        }

        public User getUser() {
            return user;
        }

        public String getCouponDiscount() {
            return couponDiscount;
        }

        public int getStatus() {
            return status;
        }
    }
}