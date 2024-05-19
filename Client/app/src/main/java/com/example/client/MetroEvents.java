package com.example.client;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

import com.example.client.Activities.OrganizerDashboard;
import com.example.client.Activities.UserDashboard;
import com.example.client.Classes.Response;
import com.example.client.Entities.Event;
import com.example.client.Entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MetroEvents extends Application {
    private RxDataStore<Preferences> dataStore;
    public static SocketClient socketClient;
    private static final String USER_KEY = "user_key";
    private static final String EVENTS_KEY = "events_key";
    private static final HashMap<UUID, Event> EVENTS = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        dataStore = new RxPreferenceDataStoreBuilder(this.getApplicationContext(), "settings").build();
        socketClient = new SocketClient(this);
        Thread thread = new Thread(socketClient);
        thread.start();

        getUser(new UserFetchCallback() {
            @Override
            public void onUserFetched(User user) {
                if (user != null) {
                    Intent intent = null;
                    switch (user.privilege){
                        case USER:
                            intent = new Intent(getApplicationContext(), UserDashboard.class);
                            break;
                        case ORGANIZER:
                            intent = new Intent(getApplicationContext(), OrganizerDashboard.class);
                            break;
                        case ADMIN:
                            //TODO ADMIN DASHBOARD
//                                    intent = new Intent(Auth.this, UserDashboard.class);
//                                    startActivity(intent);
                            break;
                    }
                    assert intent != null;
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    //            optional
                    //            finish();
                }
            }
        });
        //TODO ADD LOADING ANIMATION FOR OPERATIONS eg. LOGIN / REGISTER / CREATE EVENT
        socketClient.setResponseListener(new SocketClient.ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), response.message, Toast.LENGTH_SHORT).show();
                        if(Objects.equals(response.operation, "login") && response.status){
                            getUser(new UserFetchCallback() {
                                @Override
                                public void onUserFetched(User user) {
                                    if (user != null) {
                                        Intent intent = null;
                                        switch (user.privilege){
                                            case USER:
                                                intent = new Intent(getApplicationContext(), UserDashboard.class);
                                                break;
                                            case ORGANIZER:
                                                intent = new Intent(getApplicationContext(), OrganizerDashboard.class);
                                                break;
                                            case ADMIN:
                                                //TODO ADMIN DASHBOARD
//                                    intent = new Intent(Auth.this, UserDashboard.class);
//                                    startActivity(intent);
                                                break;
                                        }
                                        assert intent != null;
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public RxDataStore<Preferences> getDataStore() {
        return dataStore;
    }


    private Gson getGson() {
        return new GsonBuilder()
//                .registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter())
//                .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
                .create();
    }

    public void saveUser(User user) {
        Gson gson = getGson();
        String userJson = gson.toJson(user);

        Preferences.Key<String> userKey = PreferencesKeys.stringKey(USER_KEY);

        dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.set(userKey, userJson);
            return Single.just(mutablePreferences);
        }).subscribe();
    }

    public interface UserFetchCallback {
        void onUserFetched(User user);
    }

    @SuppressLint("CheckResult")
    public void getUser(UserFetchCallback callback) {
        Preferences.Key<String> userKey = PreferencesKeys.stringKey(USER_KEY);
        Gson gson = getGson();

        dataStore.data()
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // Ensure the callback is executed on the main thread
                .subscribe(preferences -> {
                    String userJson = preferences.get(userKey);
                    if (userJson != null) {
                        Type userType = User.class;
                        User user = gson.fromJson(userJson, userType);
                        // Call the callback method with the fetched user
                        callback.onUserFetched(user);
                    } else {
                        // If user data is not available, call the callback with null
                        callback.onUserFetched(null);
                    }
                }, throwable -> {
                    // Handle any errors that occur during user data retrieval
                    throwable.printStackTrace();
                    // Call the callback with null to indicate failure
                    callback.onUserFetched(null);
                });
    }

    public void deleteUser() {
        Preferences.Key<String> userKey = PreferencesKeys.stringKey(USER_KEY);

        dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.remove(userKey);
            return Single.just(mutablePreferences);
        }).subscribe();
    }


    public void saveEvent(Event event) {
        Gson gson = getGson();
        EVENTS.put(event.id, event);

        String eventsJson = gson.toJson(EVENTS);

        Preferences.Key<String> eventsKey = PreferencesKeys.stringKey(EVENTS_KEY);
        dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.set(eventsKey, eventsJson);
            return Single.just(mutablePreferences);
        }).subscribe();
    }

    public void saveEvents(HashMap<UUID, Event> events){
        Gson gson = getGson();

        synchronized (EVENTS) {
            EVENTS.clear();
            EVENTS.putAll(events);
        }

        String eventsJson = gson.toJson(EVENTS);

        Preferences.Key<String> eventsKey = PreferencesKeys.stringKey(EVENTS_KEY);

        // Update the data store asynchronously
        dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.set(eventsKey, eventsJson);
            return Single.just(mutablePreferences);
        }).subscribe();
    }
    public interface EventsFetchCallback {
        void onEventsFetched(HashMap<UUID, Event> events);
    }

    @SuppressLint("CheckResult")
    public void getEvents(EventsFetchCallback callback) {
        Preferences.Key<String> eventsKey = PreferencesKeys.stringKey(EVENTS_KEY);
        Gson gson = getGson();

        dataStore.data()
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(preferences -> {
                    String eventsJson = preferences.get(eventsKey);
                    if (eventsJson != null) {
                        Type eventsType = new TypeToken<HashMap<UUID, Event>>() {}.getType();
                        HashMap<UUID, Event> events = gson.fromJson(eventsJson, eventsType);
                        callback.onEventsFetched(events);
                    } else {
                        callback.onEventsFetched(new HashMap<>());
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    callback.onEventsFetched(new HashMap<>());
                });
    }
}