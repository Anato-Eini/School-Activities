package com.example.client;

import android.app.Application;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

public class MetroEvents extends Application {
    private RxDataStore<Preferences> dataStore;

    @Override
    public void onCreate() {
        super.onCreate();
        dataStore = new RxPreferenceDataStoreBuilder(this, "settings").build();
    }

    public RxDataStore<Preferences> getDataStore() {
        return dataStore;
    }
}
