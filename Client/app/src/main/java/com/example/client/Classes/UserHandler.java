package com.example.client.Classes;

import com.example.client.Entities.Comment;
import com.example.client.Entities.User;

import java.util.HashMap;
import java.util.UUID;

public class UserHandler {
    GetUsersCallback getUsersCallback;

    public interface GetUsersCallback{
        void onUsersFetched(boolean success, HashMap<UUID, User> users);
    }

    public void setNotifyFetchedUsers(GetUsersCallback callback) {
        this.getUsersCallback = callback;
    }

    public void notifyUsersFetched(boolean success, HashMap<UUID, User> users){
        if(getUsersCallback != null){
            getUsersCallback.onUsersFetched(success, users);
        }
    }
}
