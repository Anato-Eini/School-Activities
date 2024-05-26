package com.example.client.Activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.Fragments.CreateEvent;
import com.example.client.Fragments.Events;
import com.example.client.Fragments.ManageEvents;
import com.example.client.Fragments.Profile;
import com.example.client.MetroEvents;
import com.example.client.R;

public class OrganizerDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_organizer_dashboard);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, Events.class, null)
                    .commit();
        }

        Button navigate_home_btn_ = findViewById(R.id.navigate_home_btn_);
        navigate_home_btn_.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, Events.class, null)
                    .commit();
        });


        Button navigate_manage_events_btn_ = findViewById(R.id.navigate_manage_events_btn_);
        navigate_manage_events_btn_.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, ManageEvents.class, null)
                    .commit();
        });

        Button navigate_profile_btn = findViewById(R.id.navigate_profile_btn_);

        navigate_profile_btn.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, Profile.class, null)
                    .commit();
        });

        Button navigate_create_event_btn_ = findViewById(R.id.navigate_create_event_btn_);
        navigate_create_event_btn_.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, CreateEvent.class, null)
                    .commit();
        });
    }
}