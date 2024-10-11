package com.example.client.Classes;

import com.example.client.Entities.EventParticipantStatusNotification;
import com.example.client.Entities.User;

import java.util.HashMap;
import java.util.UUID;

public class UserHandler {
    GetUsersCallback getUsersCallback;
    GetUserEventParticipantStatusNotifications getUserEventParticipantStatusNotifications;

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

    public interface GetUserEventParticipantStatusNotifications{
        void onNotificationsFetched(boolean success, HashMap<UUID, EventParticipantStatusNotification> users);
    }

    public void setNotifyFetchedEventParticipantStatusNotifications(GetUserEventParticipantStatusNotifications callback) {
        this.getUserEventParticipantStatusNotifications = callback;
    }

    public void notifyEventParticipantStatusNotificationsFetched(boolean success, HashMap<UUID, EventParticipantStatusNotification> eventParticipantStatusNotifications){
        if(getUserEventParticipantStatusNotifications != null){
            getUserEventParticipantStatusNotifications.onNotificationsFetched(success, eventParticipantStatusNotifications);
        }
    }

}
