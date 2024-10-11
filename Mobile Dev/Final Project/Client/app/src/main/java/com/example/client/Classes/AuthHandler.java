package com.example.client.Classes;

import com.example.client.Entities.Comment;
import com.example.client.Entities.User;

public class AuthHandler {

    LoginCallback loginCallback;

    public interface LoginCallback{
        void onLogin(boolean success, User user);
    }

    public void setNotifyLogin(LoginCallback callback) {
        this.loginCallback = callback;
    }

    public void notifyOnLogin(boolean success, User user) {
        if (loginCallback != null) {
            loginCallback.onLogin(success, user);
        }
    }
}
