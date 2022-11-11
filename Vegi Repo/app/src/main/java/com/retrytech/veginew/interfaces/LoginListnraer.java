package com.retrytech.veginew.interfaces;

import com.retrytech.veginew.models.UserRoot;

public interface LoginListnraer {
    void onLoginSuccess(UserRoot.User user);

    void onDismiss();

    void onFailure();
}
