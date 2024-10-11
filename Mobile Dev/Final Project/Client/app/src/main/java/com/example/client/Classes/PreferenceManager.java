package com.example.client.Classes;


import android.annotation.SuppressLint;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.rxjava2.RxDataStore;

import com.example.client.MetroEvents;

public class PreferenceManager {
    public static Preferences.Key<String> USERNAME = PreferencesKeys.stringKey("username");
    public static Single<Preferences> updateUsername(RxDataStore<Preferences> dataStore, String newUsername) {
        return dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(USERNAME, newUsername);
            return Single.just(mutablePreferences);
        });
    }

    public static Flowable<String> getUsername(RxDataStore<Preferences> dataStore) {
        return dataStore.data()
                .map(prefs -> prefs.get(USERNAME) != null ? prefs.get(USERNAME) : "Default Username");
    }

    public static void printUsername(RxDataStore<Preferences> dataStore) {
        getUsername(dataStore)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        value -> System.out.println("Username: " + value), // onNext
                        throwable -> System.err.println("Error: " + throwable) // onError
                );
    }
}