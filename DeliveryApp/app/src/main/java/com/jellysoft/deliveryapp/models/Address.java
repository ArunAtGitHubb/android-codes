
package com.jellysoft.deliveryapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Address {

    @Expose
    private List<Datum> data;
    @Expose
    private String message;
    @Expose
    private Long status;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @SuppressWarnings("unused")
    public static class Datum {

        @SerializedName("address_type")
        private Long addressType;
        @SerializedName("alt_mobile_number")
        private String altMobileNumber;
        @Expose
        private String area;
        @Expose
        private String city;
        @SerializedName("delivery_address_id")
        private String deliveryAddressId;
        @SerializedName("first_name")
        private String firstName;

        @SerializedName("is_default")
        private Long isDefault;
        @Expose
        private String landmark;
        @SerializedName("last_name")
        private String lastName;
        @Expose
        private String latitude;
        @Expose
        private String longitude;
        @SerializedName("mobile_number")
        private String mobileNumber;
        @Expose
        private String pincode;


        @SerializedName("user_id")
        private String userId;

        @SerializedName("address")
        private String addressLine;

        public String getAddressLine() {
            return addressLine;
        }

        public void setAddressLine(String addressLine) {
            this.addressLine = addressLine;
        }

        public Long getAddressType() {
            return addressType;
        }

        public void setAddressType(Long addressType) {
            this.addressType = addressType;
        }

        public String getAltMobileNumber() {
            return altMobileNumber;
        }

        public void setAltMobileNumber(String altMobileNumber) {
            this.altMobileNumber = altMobileNumber;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDeliveryAddressId() {
            return deliveryAddressId;
        }

        public void setDeliveryAddressId(String deliveryAddressId) {
            this.deliveryAddressId = deliveryAddressId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }



        public Long getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(Long isDefault) {
            this.isDefault = isDefault;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }



        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }
}
