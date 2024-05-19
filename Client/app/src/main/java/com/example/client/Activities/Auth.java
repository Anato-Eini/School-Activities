package com.example.client.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.rxjava2.RxDataStore;

import com.example.client.Classes.Response;
import com.example.client.Entities.User;
import com.example.client.Fragments.Login;
import com.example.client.MetroEvents;
import com.example.client.R;
import com.example.client.SocketClient;

import java.util.Objects;

public class Auth extends AppCompatActivity {
    Button btnSelectEventPhoto;
    Button btnCreateEvent;

    Button btnNavigateRegister;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, Login.class, null)
                    .commit();
        }
    }
}
