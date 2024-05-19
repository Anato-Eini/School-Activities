package com.example.client.Activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.Fragments.CreateEvent;
import com.example.client.Fragments.Events;
import com.example.client.Fragments.Profile;
import com.example.client.R;

public class UserDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_dashboard);

        //TODO KUNG GI BACK NI USER DLI DAPAT MU BALIK SA LOGIN MU QUIT DAPAT, THEN IF NAA SA PROFILE TAB THEN NI BACK MUBALIK SA HOME
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, Events.class, null)
                    .commit();
        }

        Button navigate_home_btn_ = findViewById(R.id.navigate_home_btn_);
        navigate_home_btn_.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, Events.class, null)
                    .commit();
        });

        Button navigate_profile_btn = findViewById(R.id.navigate_profile_btn_);

        navigate_profile_btn.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, Profile.class, null)
                    .commit();
        });
    }
}