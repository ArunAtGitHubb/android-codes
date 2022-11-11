package com.jellysoft.deliveryapp.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("image")
    private String image;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("login_type")
    private String loginType;

    @SerializedName("identity")
    private String identity;

    @SerializedName("device_token")
    private String deviceToken;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("device_type")
    private int deviceType;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("token")
    private String token;

    public String getImage() {
        return image;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getLoginType() {
        return loginType;
    }

    public String getIdentity() {
        return identity;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getToken() {
        return token;
    }
}