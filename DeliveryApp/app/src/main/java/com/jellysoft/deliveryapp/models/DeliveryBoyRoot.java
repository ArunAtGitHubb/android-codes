package com.jellysoft.deliveryapp.models;

import com.google.gson.annotations.SerializedName;

public class DeliveryBoyRoot {

    @SerializedName("data")
    private Data data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public static class Data {

        @SerializedName("image")
        private String image;

        @SerializedName("is_avialable")
        private int isAvialable;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("device_type")
        private int deviceType;

        @SerializedName("token")
        private String token;

        @SerializedName("number")
        private long number;

        @SerializedName("password")
        private String password;

        @SerializedName("updated_at")
        private String updatedAt;

        @SerializedName("device_token")
        private String deviceToken;

        @SerializedName("payment")
        private String payment;

        @SerializedName("id")
        private int id;

        @SerializedName("fullname")
        private String fullname;

        @SerializedName("username")
        private String username;

        public String getImage() {
            return image;
        }

        public int getIsAvialable() {
            return isAvialable;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public String getToken() {
            return token;
        }

        public long getNumber() {
            return number;
        }

        public String getPassword() {
            return password;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public String getPayment() {
            return payment;
        }

        public int getId() {
            return id;
        }

        public String getFullname() {
            return fullname;
        }

        public String getUsername() {
            return username;
        }
    }
}